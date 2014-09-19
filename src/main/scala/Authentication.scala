package com.aesthetikx.twitterfilesystem

import java.io.{File, PrintWriter}

import org.yaml.snakeyaml.Yaml

import scala.io.Source

import scalaj.http.Token

object Authentication {

  def userAuth(): Token = {
    val token: Token = Twitter.getVerifierToken()
    println("Stored authentication token not found.")
    println("Visit https://api.twitter.com/oauth/authorize?oauth_token=" + token.key)
    print("And input the provided pin here: ")
    val pin: String = readLine().trim
    Twitter.getAccessToken(token, pin)
  }

  def saveToken(token: Token) {
    val map: java.util.Map[String, String] = new java.util.HashMap[String, String]
    map.put("key", token.key)
    map.put("secret", token.secret)
    val writer = new PrintWriter(new File("access_token.yaml"))
    writer.write(new Yaml().dump(map))
    writer.close
  }

  def loadToken(): Option[Token] = {
    try {
      val tokenMap = new Yaml().load(Source.fromFile("access_token.yaml").mkString)
        .asInstanceOf[java.util.Map[String, String]]

      (tokenMap.containsKey("key"), tokenMap.containsKey("secret")) match {
        case (true, true) => Some(Token(tokenMap.get("key"), tokenMap.get("secret")))
        case _ => None
      }
    } catch {
      case e: Exception => None
    }
  }

}
