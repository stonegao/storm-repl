storm-repl
==========

storm-repl is a tool for inspecting your running topologies

(storm-repl requires lein 1.x)

## Usage

### start storm-repl

run `script/repl` (you need to run `lein deps` once)

### (help)

```
storm-repl.repl=> (help)
(ls) - list running topologies
(print-conf) - print storm cluster configurations
(print-supervisors) - print supervisors in this cluster
(supervisor-info supervisor-id) - print supervisor info
(assignments) - print cluster assignments info
(storm-base topo-name) - print basic info for the specified topology
(assignment-info topo-name) - print assignment for the specified topology
(worker-heartbeat topo-name node port) - print worker heartbeat info for the worker at node port for the specified topology
(executor-beats topo-name) - print executor heartbeats info for the specified topology
```

### (ls)

```
storm-repl.repl=> (ls)
67749 [main] INFO  backtype.storm.thrift  - Connecting to Nimbus at localhost:6627
exclamation-topo               ACTIVE               16 tasks             3 workers            1139231 uptime secs

```

### (print-conf)

```
storm-repl.repl=> (print-conf)
{"dev.zookeeper.path" "/tmp/dev-storm-zookeeper",
 "topology.tick.tuple.freq.secs" nil,
 "topology.fall.back.on.java.serialization" true,
 "zmq.linger.millis" 5000,
 "topology.skip.missing.kryo.registrations" false,
 "ui.childopts" "-Xmx768m",
 "storm.zookeeper.session.timeout" 20000,
 "nimbus.reassign" true,
 "nimbus.monitor.freq.secs" 10,
 "java.library.path" "/usr/local/lib:/opt/local/lib:/usr/lib",
 "topology.executor.send.buffer.size" 1024,
 "storm.local.dir" "storm-local",
 "supervisor.worker.start.timeout.secs" 120,
 "topology.enable.message.timeouts" true,
 "nimbus.cleanup.inbox.freq.secs" 600,
 "nimbus.inbox.jar.expiration.secs" 3600,
 "topology.worker.shared.thread.pool.size" 4,
 "nimbus.host" "localhost",
 "storm.zookeeper.port" 2181,
 "transactional.zookeeper.port" nil,
 "topology.executor.receive.buffer.size" 1024,
 "transactional.zookeeper.servers" nil,
 "storm.zookeeper.root" "/storm",
 "supervisor.enable" true,
 "storm.zookeeper.servers" ["localhost"],
 "transactional.zookeeper.root" "/transactional",
 "topology.acker.executors" 1,
 "topology.transfer.buffer.size" 1024,
 "topology.worker.childopts" nil,
 "worker.childopts" "-Xmx768m",
 "supervisor.heartbeat.frequency.secs" 5,
 "drpc.port" 3772,
 "supervisor.monitor.frequency.secs" 3,
 "topology.receiver.buffer.size" 8,
 "task.heartbeat.frequency.secs" 3,
 "topology.tasks" nil,
 "topology.spout.wait.strategy"
 "backtype.storm.spout.SleepSpoutWaitStrategy",
 "topology.max.spout.pending" nil,
 "storm.zookeeper.retry.interval" 1000,
 "topology.sleep.spout.wait.strategy.time.ms" 1,
 "supervisor.slots.ports" [6700 6701 6702 6703],
 "topology.debug" false,
 "nimbus.task.launch.secs" 120,
 "nimbus.supervisor.timeout.secs" 60,
 "topology.message.timeout.secs" 30,
 "task.refresh.poll.secs" 10,
 "topology.workers" 1,
 "supervisor.childopts" "-Xmx1024m",
 "nimbus.thrift.port" 6627,
 "topology.stats.sample.rate" 0.05,
 "worker.heartbeat.frequency.secs" 1,
 "topology.acker.tasks" nil,
 "topology.disruptor.wait.strategy"
 "com.lmax.disruptor.BlockingWaitStrategy",
 "nimbus.task.timeout.secs" 30,
 "storm.zookeeper.connection.timeout" 15000,
 "drpc.invocations.port" 3773,
 "zmq.threads" 1,
 "storm.zookeeper.retry.times" 5,
 "topology.state.synchronization.timeout.secs" 60,
 "supervisor.worker.timeout.secs" 30,
 "nimbus.file.copy.expiration.secs" 600,
 "drpc.request.timeout.secs" 600,
 "storm.local.mode.zmq" false,
 "ui.port" 8080,
 "nimbus.childopts" "-Xmx1024m",
 "storm.cluster.mode" "distributed",
 "topology.optimize" true,
 "topology.max.task.parallelism" nil}

```

### (print-supervisors)

```
storm-repl.repl=> (print-supervisors)
#<ArrayList [2d54d7e6-baa6-456d-ad9f-b1f523a9be6c]>

```

### (supervisor-info supervisor-id)

```
storm-repl.repl=> (supervisor-info "2d54d7e6-baa6-456d-ad9f-b1f523a9be6c")
{:time-secs 1356605168,
 :hostname "10.18.101.46",
 :meta (6700 6701 6702 6703),
 :scheduler-meta nil,
 :uptime-secs 85284}
```

### (assignments)

```
storm-repl.repl=> (assignments)
#<ArrayList [exclamation-topo-1-1355465826]>

```

### (storm-base topo-name)

```
storm-repl.repl=> (storm-base "exclamation-topo")
272022 [main] INFO  backtype.storm.thrift  - Connecting to Nimbus at localhost:6627
{:storm-name "exclamation-topo",
 :launch-time-secs 1355465826,
 :status {:type :active},
 :num-workers 3,
 :component->executors
 {"exclaim2" 2, "exclaim1" 3, "__acker" 1, "word" 10, "__system" 0}}
```

### (assignment-info topo-name)

```
storm-repl.repl=> (assignment-info "exclamation-topo")
317556 [main] INFO  backtype.storm.thrift  - Connecting to Nimbus at localhost:6627
{:master-code-dir
 "storm-local/nimbus/stormdist/exclamation-topo-1-1355465826",
 :node->host {"2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" "10.18.101.46"},
 :executor->node+port
 {[2 2] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6701],
  [3 3] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6702],
  [4 4] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6700],
  [5 5] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6701],
  [6 6] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6702],
  [7 7] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6700],
  [8 8] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6701],
  [9 9] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6702],
  [10 10] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6700],
  [11 11] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6701],
  [12 12] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6702],
  [13 13] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6700],
  [14 14] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6701],
  [15 15] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6702],
  [16 16] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6700],
  [1 1] ["2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6700]},
 :executor->start-time-secs
 {[2 2] 1356538342,
  [3 3] 1356538342,
  [4 4] 1356538342,
  [5 5] 1356538342,
  [6 6] 1356538342,
  [7 7] 1356538342,
  [8 8] 1356538342,
  [9 9] 1356538342,
  [10 10] 1356538342,
  [11 11] 1356538342,
  [12 12] 1356538342,
  [13 13] 1356538342,
  [14 14] 1356538342,
  [15 15] 1356538342,
  [16 16] 1356538342,
  [1 1] 1356538342}}
```

### (worker-heartbeat topo-name node port)

```
storm-repl.repl=> (worker-heartbeat "exclamation-topo" "2d54d7e6-baa6-456d-ad9f-b1f523a9be6c" 6702)
436994 [main] INFO  backtype.storm.thrift  - Connecting to Nimbus at localhost:6627
{:storm-id "exclamation-topo-1-1355465826",
 :executor-stats
 {[15 15]
  {:type :spout,
   :complete-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted
   {:all-time {"default" 289580},
    600 {"default" 5900},
    10800 {"default" 103060},
    86400 {"default" 289580}},
   :transferred
   {:all-time {"default" 289580},
    600 {"default" 5900},
    10800 {"default" 103060},
    86400 {"default" 289580}}},
  [12 12]
  {:type :spout,
   :complete-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted
   {:all-time {"default" 289580},
    600 {"default" 5900},
    10800 {"default" 103060},
    86400 {"default" 289580}},
   :transferred
   {:all-time {"default" 289580},
    600 {"default" 5900},
    10800 {"default" 103060},
    86400 {"default" 289580}}},
  [9 9]
  {:type :spout,
   :complete-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted
   {:all-time {"default" 289580},
    600 {"default" 5900},
    10800 {"default" 103060},
    86400 {"default" 289580}},
   :transferred
   {:all-time {"default" 289580},
    600 {"default" 5900},
    10800 {"default" 103060},
    86400 {"default" 289580}}},
  [6 6]
  {:type :bolt,
   :process-latencies
   {:all-time {["exclaim1" "default"] 0.03501783508445641},
    600 {["exclaim1" "default"] 0.03656059580230196},
    10800 {["exclaim1" "default"] 0.03430611611300838},
    86400 {["exclaim1" "default"] 0.03501783508445641}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked
   {:all-time {["exclaim1" "default"] 1440980},
    600 {["exclaim1" "default"] 29540},
    10800 {["exclaim1" "default"] 515360},
    86400 {["exclaim1" "default"] 1440980}},
   :rate 20,
   :emitted
   {:all-time {"default" 1440960},
    600 {"default" 29540},
    10800 {"default" 515340},
    86400 {"default" 1440960}},
   :transferred
   {:all-time {"default" 0},
    600 {"default" 0},
    10800 {"default" 0},
    86400 {"default" 0}}},
  [3 3]
  {:type :bolt,
   :process-latencies
   {:all-time {["word" "default"] 0.05473268150613688},
    600 {["word" "default"] 0.06192893401015228},
    10800 {["word" "default"] 0.05495081203795332},
    86400 {["word" "default"] 0.05473268150613688}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked
   {:all-time {["word" "default"] 961400},
    600 {["word" "default"] 19700},
    10800 {["word" "default"] 343580},
    86400 {["word" "default"] 961400}},
   :rate 20,
   :emitted
   {:all-time {"default" 961400},
    600 {"default" 19700},
    10800 {"default" 343580},
    86400 {"default" 961400}},
   :transferred
   {:all-time {"default" 961400},
    600 {"default" 19700},
    10800 {"default" 343580},
    86400 {"default" 961400}}}},
 :uptime 67073,
 :time-secs 1356605426}

```
### (executor-beats topo-name)

```
storm-repl.repl=> (executor-beats "exclamation-topo")
521794 [main] INFO  backtype.storm.thrift  - Connecting to Nimbus at localhost:6627
{[2 2]
 {:time-secs 1356605509,
  :uptime 29267,
  :stats
  {:type :bolt,
   :process-latencies
   {:all-time {["word" "default"] 0.05127885991660962},
    600 {["word" "default"] 0.03391572456320658},
    10800 {["word" "default"] 0.05064972567138319},
    86400 {["word" "default"] 0.05127885991660962}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked
   {:all-time {["word" "default"] 964140},
    600 {["word" "default"] 19460},
    10800 {["word" "default"] 346300},
    86400 {["word" "default"] 964140}},
   :rate 20,
   :emitted
   {:all-time {"default" 964140},
    600 {"default" 19480},
    10800 {"default" 346320},
    86400 {"default" 964140}},
   :transferred
   {:all-time {"default" 964140},
    600 {"default" 19480},
    10800 {"default" 346320},
    86400 {"default" 964140}}}},
 [3 3]
 {:time-secs 1356605510,
  :uptime 67157,
  :stats
  {:type :bolt,
   :process-latencies
   {:all-time {["word" "default"] 0.05484453110415068},
    600 {["word" "default"] 0.06762295081967214},
    10800 {["word" "default"] 0.05526042268160296},
    86400 {["word" "default"] 0.05484453110415068}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked
   {:all-time {["word" "default"] 964180},
    600 {["word" "default"] 19520},
    10800 {["word" "default"] 346360},
    86400 {["word" "default"] 964180}},
   :rate 20,
   :emitted
   {:all-time {"default" 964160},
    600 {"default" 19500},
    10800 {"default" 346340},
    86400 {"default" 964160}},
   :transferred
   {:all-time {"default" 964160},
    600 {"default" 19500},
    10800 {"default" 346340},
    86400 {"default" 964160}}}},
 [4 4]
 {:time-secs 1356605510,
  :uptime 29268,
  :stats
  {:type :bolt,
   :process-latencies
   {:all-time {["word" "default"] 0.05306046280169196},
    600 {["word" "default"] 0.05020491803278689},
    10800 {["word" "default"] 0.05208453632059129},
    86400 {["word" "default"] 0.05306046280169196}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked
   {:all-time {["word" "default"] 964560},
    600 {["word" "default"] 19520},
    10800 {["word" "default"] 346360},
    86400 {["word" "default"] 964560}},
   :rate 20,
   :emitted
   {:all-time {"default" 964560},
    600 {"default" 19520},
    10800 {"default" 346360},
    86400 {"default" 964560}},
   :transferred
   {:all-time {"default" 964560},
    600 {"default" 19520},
    10800 {"default" 346360},
    86400 {"default" 964560}}}},
 [5 5]
 {:time-secs 1356605509,
  :uptime 29267,
  :stats
  {:type :bolt,
   :process-latencies
   {:all-time {["exclaim1" "default"] 0.0334237550862236},
    600 {["exclaim1" "default"] 0.03082191780821918},
    10800 {["exclaim1" "default"] 0.03214753214753215},
    86400 {["exclaim1" "default"] 0.0334237550862236}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked
   {:all-time {["exclaim1" "default"] 1445080},
    600 {["exclaim1" "default"] 29200},
    10800 {["exclaim1" "default"] 519480},
    86400 {["exclaim1" "default"] 1445080}},
   :rate 20,
   :emitted
   {:all-time {"default" 1445080},
    600 {"default" 29180},
    10800 {"default" 519480},
    86400 {"default" 1445080}},
   :transferred
   {:all-time {"default" 0},
    600 {"default" 0},
    10800 {"default" 0},
    86400 {"default" 0}}}},
 [6 6]
 {:time-secs 1356605510,
  :uptime 67157,
  :stats
  {:type :bolt,
   :process-latencies
   {:all-time {["exclaim1" "default"] 0.03506926664544612},
    600 {["exclaim1" "default"] 0.03691045796308954},
    10800 {["exclaim1" "default"] 0.0344548814290114},
    86400 {["exclaim1" "default"] 0.03506926664544612}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked
   {:all-time {["exclaim1" "default"] 1445140},
    600 {["exclaim1" "default"] 29260},
    10800 {["exclaim1" "default"] 519520},
    86400 {["exclaim1" "default"] 1445140}},
   :rate 20,
   :emitted
   {:all-time {"default" 1445120},
    600 {"default" 29240},
    10800 {"default" 519500},
    86400 {"default" 1445120}},
   :transferred
   {:all-time {"default" 0},
    600 {"default" 0},
    10800 {"default" 0},
    86400 {"default" 0}}}},
 [7 7]
 {:time-secs 1356605510,
  :uptime 29268,
  :stats
  {:type :spout,
   :complete-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted
   {:all-time {"default" 289880},
    600 {"default" 5860},
    10800 {"default" 103900},
    86400 {"default" 289880}},
   :transferred
   {:all-time {"default" 289880},
    600 {"default" 5860},
    10800 {"default" 103900},
    86400 {"default" 289880}}}},
 [8 8]
 {:time-secs 1356605509,
  :uptime 29267,
  :stats
  {:type :spout,
   :complete-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted
   {:all-time {"default" 289860},
    600 {"default" 5840},
    10800 {"default" 103900},
    86400 {"default" 289860}},
   :transferred
   {:all-time {"default" 289860},
    600 {"default" 5840},
    10800 {"default" 103900},
    86400 {"default" 289860}}}},
 [9 9]
 {:time-secs 1356605510,
  :uptime 67157,
  :stats
  {:type :spout,
   :complete-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted
   {:all-time {"default" 290440},
    600 {"default" 5860},
    10800 {"default" 103920},
    86400 {"default" 290440}},
   :transferred
   {:all-time {"default" 290440},
    600 {"default" 5860},
    10800 {"default" 103920},
    86400 {"default" 290440}}}},
 [10 10]
 {:time-secs 1356605510,
  :uptime 29268,
  :stats
  {:type :spout,
   :complete-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted
   {:all-time {"default" 289880},
    600 {"default" 5860},
    10800 {"default" 103900},
    86400 {"default" 289880}},
   :transferred
   {:all-time {"default" 289880},
    600 {"default" 5860},
    10800 {"default" 103900},
    86400 {"default" 289880}}}},
 [11 11]
 {:time-secs 1356605509,
  :uptime 29267,
  :stats
  {:type :spout,
   :complete-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted
   {:all-time {"default" 289840},
    600 {"default" 5820},
    10800 {"default" 103880},
    86400 {"default" 289840}},
   :transferred
   {:all-time {"default" 289840},
    600 {"default" 5820},
    10800 {"default" 103880},
    86400 {"default" 289840}}}},
 [12 12]
 {:time-secs 1356605510,
  :uptime 67157,
  :stats
  {:type :spout,
   :complete-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted
   {:all-time {"default" 290420},
    600 {"default" 5860},
    10800 {"default" 103900},
    86400 {"default" 290420}},
   :transferred
   {:all-time {"default" 290420},
    600 {"default" 5860},
    10800 {"default" 103900},
    86400 {"default" 290420}}}},
 [13 13]
 {:time-secs 1356605510,
  :uptime 29268,
  :stats
  {:type :spout,
   :complete-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted
   {:all-time {"default" 289880},
    600 {"default" 5860},
    10800 {"default" 103920},
    86400 {"default" 289880}},
   :transferred
   {:all-time {"default" 289880},
    600 {"default" 5860},
    10800 {"default" 103920},
    86400 {"default" 289880}}}},
 [14 14]
 {:time-secs 1356605509,
  :uptime 29267,
  :stats
  {:type :spout,
   :complete-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted
   {:all-time {"default" 289860},
    600 {"default" 5840},
    10800 {"default" 103900},
    86400 {"default" 289860}},
   :transferred
   {:all-time {"default" 289860},
    600 {"default" 5840},
    10800 {"default" 103900},
    86400 {"default" 289860}}}},
 [15 15]
 {:time-secs 1356605510,
  :uptime 67157,
  :stats
  {:type :spout,
   :complete-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted
   {:all-time {"default" 290420},
    600 {"default" 5860},
    10800 {"default" 103900},
    86400 {"default" 290420}},
   :transferred
   {:all-time {"default" 290420},
    600 {"default" 5860},
    10800 {"default" 103900},
    86400 {"default" 290420}}}},
 [16 16]
 {:time-secs 1356605510,
  :uptime 29268,
  :stats
  {:type :spout,
   :complete-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted
   {:all-time {"default" 289860},
    600 {"default" 5840},
    10800 {"default" 103900},
    86400 {"default" 289860}},
   :transferred
   {:all-time {"default" 289860},
    600 {"default" 5840},
    10800 {"default" 103900},
    86400 {"default" 289860}}}},
 [1 1]
 {:time-secs 1356605510,
  :uptime 29268,
  :stats
  {:type :bolt,
   :process-latencies {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :failed {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :acked {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :rate 20,
   :emitted {:all-time {}, 600 {}, 10800 {}, 86400 {}},
   :transferred {:all-time {}, 600 {}, 10800 {}, 86400 {}}}}}

```

## License

Copyright (C) 2012 Stone Gao

Distributed under the Eclipse Public License, the same as Clojure.