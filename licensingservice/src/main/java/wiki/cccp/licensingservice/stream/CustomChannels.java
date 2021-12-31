package wiki.cccp.licensingservice.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {

    String CHANNEL_NAME = "orgInput";

    @Input(CustomChannels.CHANNEL_NAME)
    SubscribableChannel orgInput();
}
