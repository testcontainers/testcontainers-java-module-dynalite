package org.testcontainers.dynamodb;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

/**
 * Container for Dynalite, a DynamoDB clone.
 */
public class DynaliteContainer extends GenericContainer<DynaliteContainer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynaliteContainer.class);

    public DynaliteContainer() {
        this("richnorth/dynalite:v1.2.1-1");
    }

    public DynaliteContainer(String imageName) {
        super(imageName);
        withExposedPorts(4567);
        withLogConsumer(new Slf4jLogConsumer(LOGGER));
    }

    /**
     * Gets a preconfigured {@link AmazonDynamoDB} client object for connecting to this
     * container.
     * @return preconfigured client
     */
    public AmazonDynamoDB getClient() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(getEndpointConfiguration())
                .withCredentials(getCredentials())
                .build();
    }

    /**
     * Gets {@link com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration}
     * that may be used to connect to this container.
     * @return endpoint configuration
     */
    public AwsClientBuilder.EndpointConfiguration getEndpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration("http://" +
                this.getContainerIpAddress() + ":" +
                this.getMappedPort(4567), null);
    }

    /**
     * Gets an {@link AWSCredentialsProvider} that may be used to connect to this container.
     * @return dummy AWS credentials
     */
    public AWSCredentialsProvider getCredentials() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials("dummy", "dummy"));
    }
}
