package org.apache.camel.component.sparkrest;

import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;

/**
 * Test based on the {@link CamelSparkParamTest} test which adds query parameters to the request.
 * @author <a href="jonathan.elliott@capgemini.com">Jon Elliott</a>
 */
public class CamelSparkQueryParamTest extends BaseSparkTest {

    @Test
    public void testSparkGet() throws Exception {
        getMockEndpoint("mock:foo").expectedMessageCount(1);
        getMockEndpoint("mock:foo").expectedHeaderReceived("name", "world");
        getMockEndpoint("mock:foo").expectedHeaderReceived("surname", "universe");

        String out = template.requestBody("http://localhost:" + getPort() + "/hello/world?surname=universe", null, String.class);
        assertEquals("Bye world", out);

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("spark-rest:get:/hello/:name")
                        .to("mock:foo")
                        .transform().simple("Bye ${header.name}");
            }
        };
    }
}
