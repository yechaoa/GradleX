#!/bin/bash
./gradlew clean
./gradlew assembleDebug --stacktrace -PisDebug=true
adb install -r  build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.yechaoa.gradlex/.MainActivity
echo "install and start app"