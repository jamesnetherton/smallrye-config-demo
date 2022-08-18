package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.IsEqual.equalTo;

@QuarkusTest
public class PropertyResolutionTest {

    @Test
    public void propertyResolution() {
        RestAssured.get("/test/properties")
                .then()
                .body(
                        "'camel.threadpool.pool-size'", equalTo("5"),
                        "'camel.threadpool.max-pool-size'", equalTo("10"),
                        "'camel.threadpool.max-queue-size'", equalTo("20"),
                        "'camel.threadpool.rejectedPolicy'", equalTo("DiscardOldest"),
                        "'camel.threadpool.config[customPool].id'", equalTo("customPool"),
                        "'camel.threadpool.config[customPool].pool-size'", equalTo("1"),
                        "'camel.threadpool.config[customPool].rejectedPolicy'", equalTo("Abort")
                );
    }
}