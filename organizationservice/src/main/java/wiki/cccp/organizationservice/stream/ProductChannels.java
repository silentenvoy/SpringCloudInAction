package wiki.cccp.organizationservice.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductChannels {
    String CHANNEL_NAME = "orgOutput";

    @Output(ProductChannels.CHANNEL_NAME)
    MessageChannel orgOutput();
}
