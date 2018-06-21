package services

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.typesafe.scalalogging.LazyLogging
import javax.inject.Singleton

import scala.util.Try

/**
  * Created by Peerapat A on Mar 20, 2017
  */
@Singleton
class Json extends LazyLogging {

  private val mapper = new ObjectMapper with ScalaObjectMapper

  mapper.registerModule(DefaultScalaModule)
  mapper.setSerializationInclusion(Include.NON_NULL)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  def toText(obj: AnyRef): String = mapper.writeValueAsString(obj)

  def toClass[T: Manifest](content: String): Option[T] = {
    if (content == null || content.isEmpty)
      None
    else
      Try(mapper.readValue[T](content)).toOption
  }

}
