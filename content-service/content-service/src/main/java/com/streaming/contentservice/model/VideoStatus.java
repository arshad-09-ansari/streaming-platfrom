package com.streaming.contentservice.model;

/**
 * trace the video processing lifecycle
 * flow
 * pending -> upload ->endcoing-> encoded -> ready
 * failed
 *
 */
public enum VideoStatus {
    PENDEING, // movie added but uploaded yet
    UPLOADED,// raw video uploaded to s3
    ENCODING, // ffmpg is encoding the video
    ENCODED,// completed encoding
    READY,// hls playlist ready can be ready streamed
    FAILED // encoding failed



}
