for var in {1..25}
do
  if ["$var" -gt "10"] ; then
    var = day0$var
  else
    var = day$var
  fi
  if [grep -o "$var" README.md | wc -c | -gt "0"] ; then
    echo $var
  fi
done
