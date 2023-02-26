package org.sbe;

import com.aeroncookbook.sbe.MessageHeaderDecoder;
import com.aeroncookbook.sbe.MessageHeaderEncoder;
import com.aeroncookbook.sbe.SampleEnum;
import com.aeroncookbook.sbe.SampleSimpleDecoder;
import com.aeroncookbook.sbe.SampleSimpleEncoder;
import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;

public class MySamples {

    public static void main(String[] args) {

        final MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();
        SampleSimpleEncoder encoder = new SampleSimpleEncoder();
        ByteBuffer buffer = ByteBuffer.allocateDirect(128);
        UnsafeBuffer unsafeBuffer = new UnsafeBuffer(buffer);

        encoder.wrapAndApplyHeader(unsafeBuffer, 0, messageHeaderEncoder);

        encoder.message("Hello World !");
        encoder.sequence(1);
        encoder.enumField(SampleEnum.VALUE_3);

        int encodedLength = encoder.encodedLength() + MessageHeaderEncoder.ENCODED_LENGTH;

        System.out.println(encodedLength);

        MessageHeaderDecoder headerDecoder = new MessageHeaderDecoder();
        SampleSimpleDecoder decoder = new SampleSimpleDecoder();

        headerDecoder.wrap(unsafeBuffer, 0);

        int version = headerDecoder.version();
        int blockLength = headerDecoder.blockLength();

        int offset = headerDecoder.encodedLength();

        decoder.wrap(unsafeBuffer, offset, blockLength, version);

        System.out.println(decoder.enumField());
    }

}
