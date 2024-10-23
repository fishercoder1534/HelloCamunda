package com.fishercoder.camunda.chargecard;

import java.awt.Desktop;
import java.net.URI;
import java.util.logging.Logger;

import org.camunda.bpm.client.ExternalTaskClient;

/**
 * 1. Once you run this program, go to: http://localhost:8080/camunda/app/welcome/default/#!/login, use this user and password to login: demo, demo.
 * 2. After logging in, go to: http://localhost:8080/camunda/app/cockpit/default/#/dashboard, you should be able to see Payment Retrieval process on the dashboard if you followed along: https://docs.camunda.org/get-started/quick-start/service-task/
 * 3. You can call this process/service via curl or REST APIs:
 *     ${PROCESS_DEFINITION} is the name you'll get from logging into your local Camunda cockpit, e.g. payment-retrieval
 *    curl -H "Content-Type: application/json" -X POST -d '{"variables": {"amount": {"value":111,"type":"integer"}, "item": {"value":"item-xyz"} } }' http://localhost:8080/engine-rest/process-definition/key/${PROCESS_DEFINITION}/start
 *    or via REST API:
 *       with this endpoint: http://localhost:8080/engine-rest/process-definition/key/${PROCESS_DEFINITION}/start
 *       HTTP Method: POST
 *       Body: {"variables":{"amount":{"value":222,"type":"integer"},"item":{"value":"item-xyz"}}}
 *    Once you invoke the endpoint via either of the above two methods, you can see logs print out like:
 *          "INFO: Charging credit card with an amount of '222'$ for the item 'item-xyz'..."
 * */
public class ChargeCardWorker {
    private final static Logger LOGGER = Logger.getLogger(ChargeCardWorker.class.getName());

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000) // long polling timeout
                .build();

        // subscribe to an external task topic as specified in the process
        client.subscribe("charge-card")
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler((externalTask, externalTaskService) -> {
                    // Put your business logic here

                    // Get a process variable
                    String item = externalTask.getVariable("item");
                    Integer amount = externalTask.getVariable("amount");

                    LOGGER.info("Charging credit card with an amount of '" + amount + "'$ for the item '" + item + "'...");

                    try {
                        Desktop.getDesktop().browse(new URI("https://docs.camunda.org/get-started/quick-start/complete"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Complete the task
                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}
