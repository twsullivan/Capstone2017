rm -r ./build
mkdir -p ./build/classes

cp -r ./lib/org ./build/classes/

javac -g -cp ./build/classes ./src/*.java -d ./build/classes


jar cfe ./build/DNSResolver.jar DNSResolver -C ./build/classes .

