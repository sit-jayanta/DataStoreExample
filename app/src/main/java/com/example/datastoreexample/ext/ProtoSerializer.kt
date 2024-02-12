package com.example.datastoreexample.ext

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.example.datastoreexample.ProtoExample
import java.io.InputStream
import java.io.OutputStream

object ProtoSerializer : Serializer<ProtoExample> {
    override val defaultValue: ProtoExample = ProtoExample.getDefaultInstance()


    override suspend fun readFrom(input: InputStream): ProtoExample {
        try {
            return ProtoExample.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: ProtoExample, output: OutputStream) {
        t.writeTo(output)
    }

}