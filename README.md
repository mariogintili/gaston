# Gaston [![Build Status](https://travis-ci.org/mariogintili/gaston.svg?branch=master)](https://travis-ci.org/mariogintili/gaston) [![Deploy on Heroku](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

![Being this cool](https://octodex.github.com/images/front-end-conftocat.png)

This is a small clojure server using redis as a data-store, purposed to be used in [Lighting fast approach](http://ember-cli.github.io/ember-cli-deploy/docs/v0.4.x/lightning-approach-workflow/) to server Ember-CLI apps.

# Installation

Requirements:

- Clojure `^1.6.0`
- Leiningen `^2.5.1`
- Redis

# Usage

This server requires a [Redis](http://redis.io/) store. First you'll need to define some env vars on your machine:

| Name         | Value                     | Description                     |
|--------------|---------------------------|---------------------------------|
| PROJECT_NAME | codecards-app             | The name of your Ember-CLI app. |
| REDIS_URI    | redis://host:port/dbindex | The URI to connect to Redis.    |


This server will basically look for the presence of an `index_key` query param in your request and build a redis key with the value, i.e:

- Given `foo.com?index_key=4d68d5f`
- Gaston will serve anything in redis stored under `<project-name>:4d68d5f` :confetti_ball: :confetti_ball:

You can see a demo live:

- Without parameters given, defaults to `current`  see [here](https://gaston-codecards.herokuapp.com/signup)
- With a parameter, see how you get a brand new app! see [here](https://gaston-codecards.herokuapp.com/signup?index_key=codecards:4d68d5f)
