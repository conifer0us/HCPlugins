#### All you need to run plugin test is to have java 17 installed and accessible from command line

##### When upgrading to new version, delete old server jar and replace the next two variables
$version = "1.20.4"
$jarurl = "https://api.papermc.io/v2/projects/paper/versions/1.20.4/builds/493/downloads/paper-1.20.4-493.jar"
#####

$serverfile = ".\testenv\server$version.jar"
$plugindir = "testenv/plugins"
if (!(Test-Path $serverfile -PathType Leaf)) {
    Write-Output "Server jar not found. Downloading for $version"
    Invoke-WebRequest $jarurl -OutFile $serverfile
}

Write-Output "Building plugin..."
.\gradlew.bat build

Write-Output "Copying build output to server plugins"
If(!(Test-Path $plugindir) ){
    New-Item -ItemType Directory -Force -Path $plugindir
}
Remove-Item "testenv\plugins\*.jar"
Copy-Item "build\libs\*.jar" $plugindir

Write-Output "Starting server..."
Set-Location -Path ".\testenv"
java -Xmx4G -Xms4G -jar "server$version.jar" --nogui