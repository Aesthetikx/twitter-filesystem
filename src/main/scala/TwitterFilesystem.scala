package com.aesthetikx.twitterfilesystem

import scalaj.http.Token

object TwitterFilesystem {

  def main(argv: Array[String]) {
    val token: Token = Authentication.loadToken.getOrElse(Authentication.userAuth())
    //println(Twitter.postTweet(token, "Test Tweet"))
  }

}
