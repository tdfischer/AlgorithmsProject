var MAPOBJECTS = {
	AIR:		{value:0, name:"air",		color:"#D0D0BD"},
	WALL:		{value:1, name:"wall",		color:"#3F3F3F"},
	ENTRANCE:	{value:2, name:"entrance",	color:"#FF0000"},
	EXIT:		{value:3, name:"exit",		color:"#00FF00"},
	PATH:		{value:4, name:"path",		color:"#00FFFF"}
};

/**
  * Uses the format [column][row] to maintain x:y relations. At least I think. Things get pretty awkward in 2D land, especially because
  * JS seems to swap the pairs when accessing the thing like an array. WAT.
  */
function generateMap(xSize, ySize, density) {
	//create the 2d array
	var map = new Array(ySize);
	for (var i = 0; i < ySize; ++i) {
		map[i] = new Array(xSize);
	}
	//generate edges first
	for (var i = 0; i < ySize; ++i) {
		map[i][0] =			MAPOBJECTS.WALL;
		map[i][xSize-1] =	MAPOBJECTS.WALL;
	}
	for (var i = 0; i < xSize; ++i) {
		map[0][i] =			MAPOBJECTS.WALL;
		map[ySize-1][i] =	MAPOBJECTS.WALL;
	}
	
	//seed walls
	for (var i = 1; i < ySize-1; ++i) {
		for (var j = 1; j < xSize-1; ++j) {
			map[i][j] = (Math.floor(Math.random()*100) < density) ? MAPOBJECTS.WALL : MAPOBJECTS.AIR;
		}
	}
	
	//add a start point and an exit point
	var startAdded = false;
	var exitAdded = false;
	
	while (!startAdded || !exitAdded) {
		var testPoint = [Math.floor(Math.random()*xSize), Math.floor(Math.random()*ySize)];
		
		//a candidate point was found! add an entrance and if that's done add an exit
		if (testPoint[0] == 0 || testPoint[1] == 0 || testPoint[0] == xSize - 1 || testPoint[1] == ySize - 1) {
			if (!startAdded) {
				startAdded = true;
				map[testPoint[1]][testPoint[0]] = MAPOBJECTS.ENTRANCE;
			}
			else if (!exitAdded) {
				exitAdded = true;
				map[testPoint[1]][testPoint[0]] = MAPOBJECTS.EXIT;
			}
		}
	
	}
	return map;
}