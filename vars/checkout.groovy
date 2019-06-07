#!/usr/bin/env groovy

def call() {
node {
   stage('Checkout') {
     checkout scm
     }
    }
}
