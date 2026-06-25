# GCash Weather App

## Overview

GCash Weather App is an Android application built using Kotlin and Jetpack Compose. It allows users to register, log in, view current weather based on their location, and keep a local history of fetched weather information.

---

## Features

- User Registration
- User Login
- Splash Screen with Session Management
- Current Weather
- Current Location
- Temperature in Celsius
- Sunrise & Sunset Time
- Weather Icons
- Weather History
- Logout

---

## Tech Stack

- Kotlin
- Jetpack Compose
- MVVM
- Hilt
- Retrofit
- Room
- DataStore
- Coroutines
- StateFlow

---

## Architecture

Presentation (Compose)

↓

ViewModel

↓

Repository

↓

Remote API / Room Database

---

## Libraries

- Hilt
- Retrofit
- Room
- DataStore
- Coil
- Material 3

---

## API Setup

Create a file named:

local.properties

Add:

OPEN_WEATHER_API_KEY=YOUR_API_KEY

Do not commit your API key.

---

## Build

Open with Android Studio.

Sync Gradle.

Run the project.

---

## Unit Tests

Basic unit tests are included for:

- LoginViewModel
- RegisterViewModel
- WeatherViewModel