/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package com.dist.simplekafka.server

object CompressionCodec {
  def getCompressionCodec(codec: Int): CompressionCodec = {
    codec match {
      case NoCompressionCodec.codec => NoCompressionCodec
      case GZIPCompressionCodec.codec => GZIPCompressionCodec
      case SnappyCompressionCodec.codec => SnappyCompressionCodec
      case _ => throw new RuntimeException("%d is an unknown compression codec".format(codec))
    }
  }
  def getCompressionCodec(name: String): CompressionCodec = {
    name.toLowerCase match {
      case NoCompressionCodec.name => NoCompressionCodec
      case GZIPCompressionCodec.name => GZIPCompressionCodec
      case SnappyCompressionCodec.name => SnappyCompressionCodec
      case _ => throw new RuntimeException("%s is an unknown compression codec".format(name))
    }
  }
}

sealed trait CompressionCodec { def codec: Int; def name: String }

case object DefaultCompressionCodec extends CompressionCodec {
  val codec = GZIPCompressionCodec.codec
  val name = GZIPCompressionCodec.name
}

case object GZIPCompressionCodec extends CompressionCodec {
  val codec = 1
  val name = "gzip"
}

case object SnappyCompressionCodec extends CompressionCodec {
  val codec = 2
  val name = "snappy"
}

case object NoCompressionCodec extends CompressionCodec {
  val codec = 0
  val name = "none"
}
