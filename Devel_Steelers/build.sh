mkdir -p ./build

javac -g -cp ./lib ./src/*.java -d ./build

touch ./build/MANIFEST.MF
echo "Manifest-Version: 1.0" > ./build/MANIFEST.MF
echo "Main-Class: DNSResolver" >> ./build/MANIFEST.MF

jar cfm ./build/DNSResolver.jar ./build/MANIFEST.MF -C ./build .
