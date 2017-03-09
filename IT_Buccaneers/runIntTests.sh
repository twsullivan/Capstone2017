#! /bin/bash
rm -f temp
$1 >> temp
diff temp $2
if [ $? = 0 ]
then
echo "Test $1 ran correctly"
else
echo "Test $1 did not give expected output"
fi

