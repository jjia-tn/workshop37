package sg.edu.nus.iss.workshop37.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AppConfig {
    
    @Value("${DO_STORAGE_KEY}")
    private String accessKey;

    @Value("${DO_STORAGE_SECRETKEY}")
    private String secretKey;

    @Value("${DO_STORAGE_ENDPOINT}")
    private String endPoint;

    @Value("${DO_STORAGE_REGION}")
    private String endPointRegion;

    @Bean
    public AmazonS3 createS3Client() {

        BasicAWSCredentials cred = 
                new BasicAWSCredentials(accessKey, secretKey);
        EndpointConfiguration ep = 
                new EndpointConfiguration(endPoint, endPointRegion);

        return AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(ep)
            .withCredentials(new AWSStaticCredentialsProvider(cred))
            .build();
    }
}
