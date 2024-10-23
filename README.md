1. I created this as a new Maven project on Java11 by following this tutorial to learn Camunda: https://docs.camunda.org/get-started/quick-start/install/;
   2. install Docker onto your local machine if you haven't;
   3. `docker pull camunda/camunda-bpm-platform:run-latest`
   4. `docker run -d --name camunda -p 8080:8080 camunda/camunda-bpm-platform:run-latest`
2. Run `mvn clean install` this project first before you attempt to run from Intellij as clicking on Maven refresh button in Intellij doesn't always work, it often hangs there, while `mvn clean install` usually finishes quickly and pull your needed dependencies somehow;
3. Run `ChargeCardWorker.java` class from Intellij