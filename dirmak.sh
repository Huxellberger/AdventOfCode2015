for var in {1..25}
do
  if ["$var" -gt "10"] ; then
    mkdir day$var
  else
    mkdir day0$var
  fi
done
