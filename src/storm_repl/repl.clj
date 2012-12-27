(ns storm-repl.repl
  (:require  [backtype.storm.daemon  [nimbus :as nimbus]])
  (:use  [backtype.storm bootstrap])
  (:use  [backtype.storm.daemon common])
  (:use [backtype.storm thrift log])
  (:import [backtype.storm.generated TopologySummary]) (:use  [clojure.pprint]))

(set! *print-length* 100)
(bootstrap)

(def cluster-conf (read-storm-config))
(def storm-cluster-state (cluster/mk-storm-cluster-state cluster-conf))
(def supervisors (.supervisors storm-cluster-state nil))
(def active-topos (.active-storms storm-cluster-state))
(def storm-name->storm-id (atom {}))

(defn with-topology-info [callback]
  (with-configured-nimbus-connection nimbus
    (let [cluster-info (.getClusterInfo nimbus)
          topologies (.get_topologies cluster-info)]
      (if (or (nil? topologies) (empty? topologies))
        {}
        (let [infos (->> topologies
                         (map (fn [^TopologySummary topology]
                                  { :topology-name        (.get_name topology)
                                    :topology-id          (.get_id topology)
                                    :topology-status      (.get_status topology)
                                    :topology-num-tasks   (.get_num_tasks topology)
                                    :topology-num-workers (.get_num_workers topology)
                                    :topology-uptime-secs (.get_uptime_secs topology)
                                  })))]
          (doseq [info infos]
            (callback info)))))))

(defn ls []
  (with-topology-info
    (fn [topo-info]
      (let [msg-format "%-30s %-20s %-20s %-20s %-20s"]
        (println (format msg-format
                         (:topology-name topo-info)
                         (:topology-status topo-info)
                         (str (:topology-num-tasks topo-info) " tasks")
                         (str (:topology-num-workers topo-info) " workers")
                         (str (:topology-uptime-secs topo-info) " uptime secs")
                         ))))))

(defn topology-name-to-id [storm-name]
  (let [name->id (atom {})]
    (with-topology-info
      (fn [topo-info]
        (swap! name->id assoc (:topology-name topo-info) (:topology-id topo-info))))
    (get @name->id storm-name)))

(defn print-conf []
  (pprint cluster-conf))

(defn print-supervisors []
  (pprint supervisors))

(defn supervisor-info [supervisor-id]
  (pprint (.supervisor-info storm-cluster-state supervisor-id)))

(defn assignments []
  (pprint (.assignments storm-cluster-state nil)))

(defn assignment-info [topo-name]
  (let [storm-id (topology-name-to-id topo-name)]
    (pprint (.assignment-info storm-cluster-state storm-id nil))))

(defn storm-base [topo-name]
  (let [storm-id (topology-name-to-id topo-name)]
    (pprint (.storm-base storm-cluster-state storm-id nil))))

(defn worker-heartbeat [topo-name node port]
  (let [storm-id (topology-name-to-id topo-name)]
    (pprint (.get-worker-heartbeat storm-cluster-state storm-id node port))))

(defn executor-beats [topo-name]
  (let [storm-id (topology-name-to-id topo-name)
        executor->node+port (:executor->node+port (.assignment-info storm-cluster-state storm-id nil))]
    (pprint (.executor-beats storm-cluster-state storm-id executor->node+port))))


(defn help []
  (let [funcs [
                ["(ls)"                                             " - list running topologies"]
                ["(print-conf)"                                     " - print storm cluster configurations"]
                ["(print-supervisors)"                              " - print supervisors in this cluster"]
                ["(supervisor-info supervisor-id)"                  " - print supervisor info"]
                ["(assignments)"                                    " - print cluster assignments info"]
                ["(storm-base topo-name)"                           " - print basic info for the specified topology"]
                ["(assignment-info topo-name)"                      " - print assignment for the specified topology"]
                ["(worker-heartbeat topo-name node port)"           " - print worker heartbeat info for the worker at node port for the specified topology"]
                ["(executor-beats topo-name)"                       " - print executor heartbeats info for the specified topology"]
              ]]
    (doseq [[func desc] funcs]
      (println (str func desc)))))

;start clojure repl
(println "Welcome to storm-repl. Type (help) to get started!")
(clojure.main/repl)
