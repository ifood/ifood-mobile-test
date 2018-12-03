# Keychain

[![Version](https://img.shields.io/github/release/tink-ab/Keychain.svg)](https://github.com/tink-ab/Keychain/releases)
![Swift Version](https://img.shields.io/badge/swift-4.2-orange.svg)
[![Carthage compatible](https://img.shields.io/badge/Carthage-compatible-4BC51D.svg?style=flat)](https://github.com/Carthage/Carthage)
[![Build Status](https://travis-ci.org/tink-ab/Keychain.svg?branch=master)](https://travis-ci.org/tink-ab/Keychain)

Micro framework for easily accessing the Keychain in less than 100 lines of code.

## Installation

#### With [CocoaPods](http://cocoapods.org/)

```ruby
use_frameworks!

pod "Keychain", :git => "https://github.com/tink-ab/Keychain"
```

#### With [Carthage](https://github.com/Carthage/Carthage)

```
github "tink-ab/Keychain"
```

## Usage

### Creating an entry

``` swift
Keychain.set(password: "secretstuff", account: "user", service: "acme")
```

### Reading an entry

``` swift
let value = Keychain.get(account: "user", service: "acme")
```

### Deleting an entry

``` swift
Keychain.delete(account: "user", service: "acme")
```
