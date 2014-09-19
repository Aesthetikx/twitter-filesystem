package com.aesthetikx.twitterfilesystem

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import org.apache.commons.io.IOUtils

import org.tukaani.xz.{LZMA2Options, XZInputStream, XZOutputStream}

object Compression {

  val lzmaOptions = new LZMA2Options()

  def compress(data: Array[Byte]): Array[Byte] = {
    val stream = new ByteArrayOutputStream()
    val compressor: XZOutputStream = new XZOutputStream(stream, lzmaOptions)
    compressor.write(data)
    compressor.flush()
    compressor.close()
    stream.toByteArray
  }

  def decompress(data: Array[Byte]): Array[Byte] = {
    val stream = new ByteArrayInputStream(data)
    val decompressor: XZInputStream = new XZInputStream(stream)
    IOUtils.toByteArray(decompressor)
  }

}
