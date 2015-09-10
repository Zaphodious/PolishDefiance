<?xml version="1.0" encoding="UTF-8"?>
<tileset name="BasicSpriteSheet" tilewidth="8" tileheight="8" tilecount="90">
 <image source="BasicSpriteSheet.png" trans="008a76" width="72" height="80"/>
 <terraintypes>
  <terrain name="Hill" tile="10"/>
  <terrain name="Water" tile="13"/>
  <terrain name="Trench" tile="55"/>
  <terrain name="Forest" tile="58"/>
  <terrain name="Bunker" tile="16"/>
 </terraintypes>
 <tile id="0" terrain=",,,0">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="1" terrain=",,0,0">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="North"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="2" terrain=",,0,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="3" terrain=",,,1">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="4" terrain=",,1,1">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="North"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="5" terrain=",,1,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="6" terrain=",,,4">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="99999999"/>
   <property name="extraCostDirection" value="All"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="7" terrain=",,4,4">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="99999999"/>
   <property name="extraCostDirection" value="All"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="8" terrain=",,4,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="99999999"/>
   <property name="extraCostDirection" value="All"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="9" terrain=",0,,0">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="East"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="10" terrain="0,0,0,0">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="0"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="11" terrain="0,,0,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="West"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="12" terrain=",1,,1">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="East"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="13" terrain="1,1,1,1">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="0"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="14" terrain="1,,1,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="West"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="15" terrain=",4,,4">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="99999999"/>
   <property name="extraCostDirection" value="All"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="16" terrain="4,4,4,4">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="99999999"/>
   <property name="extraCostDirection" value="All"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="17" terrain="4,,4,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="99999999"/>
   <property name="extraCostDirection" value="All"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="18" terrain=",0,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="19" terrain="0,0,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="South"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="20" terrain="0,,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="21" terrain=",1,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="22" terrain="1,1,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="South"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="23" terrain="1,,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="24" terrain=",4,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="99999999"/>
   <property name="extraCostDirection" value="All"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="25" terrain="4,4,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="99999999"/>
   <property name="extraCostDirection" value="All"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="26" terrain="4,,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="99999999"/>
   <property name="extraCostDirection" value="All"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="27" terrain=",0,0,0">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="28" terrain="0,,0,0">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="29">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="30" terrain=",1,1,1">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="31" terrain="1,,1,1">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="32">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="33">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="34" terrain=",4,4,4">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="99999999"/>
   <property name="extraCostDirection" value="All"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="35" terrain="4,,4,4">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="99999999"/>
   <property name="extraCostDirection" value="All"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="36" terrain="0,0,,0">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="37" terrain="0,0,0,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="38">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="39" terrain="1,1,,1">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="40" terrain="1,1,1,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="41">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="42">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="43" terrain="4,4,,4">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="99999999"/>
   <property name="extraCostDirection" value="All"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="44" terrain="4,4,4,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="99999999"/>
   <property name="extraCostDirection" value="All"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="45" terrain=",,,2">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="46" terrain=",,2,2">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="North"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="47" terrain=",,2,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="48" terrain=",,,3">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="49" terrain=",,3,3">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="North"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="50" terrain=",,3,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="51">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="52">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="53">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="54" terrain=",2,,2">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="East"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="55" terrain="2,2,2,2">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="0"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="56" terrain="2,,2,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="West"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="57" terrain=",3,,3">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="East"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="58" terrain="3,3,3,3">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="0"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="59" terrain="3,,3,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="West"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="60">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="61">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="62">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="63" terrain=",2,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="64" terrain="2,2,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="South"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="65" terrain="2,,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="66" terrain=",3,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="67" terrain="3,3,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="South"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="68" terrain="3,,,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="69">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="70">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="71">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="72" terrain=",2,2,2">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="73" terrain="2,,2,2">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="74">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="75" terrain=",3,3,3">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="76" terrain="3,,3,3">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="NorthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="77">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="78">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="79">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="80">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="81" terrain="2,2,,2">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="82" terrain="2,2,2,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="83">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="84" terrain="3,3,,3">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthWest"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="85" terrain="3,3,3,">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="5"/>
   <property name="extraCostDirection" value="SouthEast"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="86">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="87">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="88">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
 <tile id="89">
  <properties>
   <property name="blocksBullets" value="false"/>
   <property name="entryCost" value="1"/>
   <property name="extraCostDirection" value="none"/>
   <property name="hidesUnit" value="false"/>
  </properties>
 </tile>
</tileset>
