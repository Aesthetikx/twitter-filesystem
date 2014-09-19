package com.aesthetikx.twitterfilesystem

import org.yaml.snakeyaml.Yaml

import scala.io.Source

import scalaj.http.{Http, Token}

object Twitter {

  val api_config = new Yaml().load(Source.fromFile("api_secrets.yaml").mkString)
    .asInstanceOf[java.util.Map[String, String]]

  val consumer = Token(api_config.get("api_key"),
    api_config.get("api_secret"))

  def getVerifierToken(): Token = {
    Http.post("https://api.twitter.com/oauth/request_token")
      .param("oauth_callback", "oob")
      .oauth(consumer).asToken
  }

  def getAccessToken(token: Token, verifier: String): Token = {
    Http.post("https://api.twitter.com/oauth/access_token")
      .oauth(consumer, token, verifier).asToken
  }

  def postTweet(token: Token, tweet: String): (Int, Map[String, List[String]], String) = {
    Http.post("https://api.twitter.com/1.1/statuses/update.json")
      .param("status", tweet)
      .oauth(consumer, token)
      .asHeadersAndParse(Http.readString)
  }

}
