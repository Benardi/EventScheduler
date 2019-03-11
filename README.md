[![Java: version](https://img.shields.io/badge/JAVA-%3E%3D%207-blue.svg)](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# Event Scheduler
Simulation of an event scheduler made of two queues of different priority. One of the classes of customer has higher priority and the system does not mitigate in any form the classical `starvation problem`. 

The analytical model can be resumed by the following table of events:

| Primary Events | Secondary Events |
|--------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Arrival of Customer of Class 1 |  1 - Schedule next arrival (Random number from 1 to 10) <br> 2 - Test server state   <br>&ensp; Free:<br>&ensp;&ensp;&ensp; Set state to busy<br>&ensp;&ensp;&ensp; Schedule service termination (Random number from 3 to 7)   <br>&ensp; Busy:<br>&ensp;&ensp;&ensp; Schedule event “put customer in queue” |
| Arrival of Customer of Class 2 | 1 - Schedule next arrival (Random number from 1 to 5) <br>  2 - Test server state <br>&ensp; Free:<br>&ensp;&ensp;&ensp; Set state to busy <br>&ensp;&ensp;&ensp; Schedule service termination (Random number from 3 to 7) <br>&ensp; Busy:<br>&ensp;&ensp;&ensp; Schedule event “put customer in queue” |
| Service Termination | 1 - Test waiting queue state <br>&ensp; Empty:<br>&ensp;&ensp;&ensp; Set server state to “free” <br>&ensp; Not empty:<br>&ensp;&ensp;&ensp; Remove customer from queue<br>&ensp;&ensp;&ensp; Schedule event “Service termination” (Random number from 3 to 7) |

## Prerequisites

* `Java >= 7`

# Execution

To start the application run the class `src/EventSchedulerApplication.java`. The simulation results will be directed to the standard output.

## Authors

* **Benardi Nunes** - *Initial work* - [Benardi](https://github.com/Benardi)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details