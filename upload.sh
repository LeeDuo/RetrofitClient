#这里是打包上传命令。当更新了版本之后，可以运行此文件上传最新的包
./gradlew clean build bintrayUpload -PbintrayUser=leeduo -PbintrayKey=f5ffcd0b2285b0cee885d875378ee8b4a9787aeb -PdryRun=false