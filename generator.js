var MAPOBJECTS = {
  AIR:		    {value:0, name:"air",		    color:"#D0D0BD", visited:false, isPath:true},
	WALL:		    {value:1, name:"wall",		  color:"#3F3F3F", visited:false, isPath:false},
	ENTRANCE:	  {value:2, name:"entrance",	color:"#FF0000", visited:false, isPath:true},
	EXIT:		    {value:3, name:"exit",		  color:"#00FF00", visited:false, isPath:true},
	PATH:		    {value:4, name:"path",		  color:"#00FFFF", visited:false, isPath:true}
};

/**
  * Uses the format [column][row] to maintain x:y relations. At least I think. Things get pretty awkward in 2D land, especially because
  * JS seems to swap the pairs when accessing the thing like an array. WAT.
  */

Object.prototype.clone = function() {
  var newObj = (this instanceof Array) ? [] : {};
  for (i in this) {
    if (i == 'clone') continue;
    if (this[i] && typeof this[i] == "object") {
      newObj[i] = this[i].clone();
    } else newObj[i] = this[i]
  } return newObj;
};

var entranceLoc;

function getEntrance() {
  return entranceLoc;
}

function generateMap(xSize, ySize, density) {
	//create the 2d array
	var map = new Array(ySize);
	for (var i = 0; i < ySize; ++i) {
		map[i] = new Array(xSize);
	}
	//generate edges first
	for (var i = 0; i < ySize; ++i) {
		map[i][0] =		    MAPOBJECTS.WALL.clone();
		map[i][xSize-1] =	MAPOBJECTS.WALL.clone();
	}
	for (var i = 0; i < xSize; ++i) {
		map[0][i] =			MAPOBJECTS.WALL.clone();
		map[ySize-1][i] =	MAPOBJECTS.WALL.clone();
	}
	
	//seed walls
	for (var i = 1; i < ySize-1; ++i) {
		for (var j = 1; j < xSize-1; ++j) {
			map[i][j] = (Math.floor(Math.random()*100) < density) ? MAPOBJECTS.WALL.clone() : MAPOBJECTS.AIR.clone();
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
				map[testPoint[1]][testPoint[0]] = MAPOBJECTS.ENTRANCE.clone();
        entranceLoc = [testPoint[0],testPoint[1]];
			}
			else if (!exitAdded) {
				exitAdded = true;
				map[testPoint[1]][testPoint[0]] = MAPOBJECTS.EXIT.clone();
			}
		}
	
	}
	return map;
}

function floodFill(x, y, prevPoints, map) {

  if (map[y][x].value==MAPOBJECTS.EXIT.value) {
    return prevPoints;
  }

  prevPoints.push([x,y]);
  map[y][x].visited = true;

  var result;
  var tempResult;
  if (x-1 >= 0 && map[y][x-1].isPath && !map[y][x-1].visited) {
    tempResult = floodFill(x-1,y,prevPoints,map);
  if (tempResult != null && tempResult.length > 0)
    result = tempResult;
  }
  if (x+1 < map[0].length && map[y][x+1].isPath && !map[y][x+1].visited) {
    tempResult = floodFill(x+1,y,prevPoints,map);
  if (tempResult != null && tempResult.length > 0)
    result = tempResult;
  }
  if (y-1 >= 0 && map[y-1][x].isPath && !map[y-1][x].visited) {
    tempResult = floodFill(x,y-1,prevPoints,map);
  if (tempResult != null && tempResult.length > 0)
    result = tempResult;
  }
  if (y+1 < map.length && map[y+1][x].isPath && !map[y+1][x].visited) {
    tempResult = floodFill(x,y+1,prevPoints,map);
  if (tempResult != null && tempResult.length > 0)
    result = tempResult;
  }
  return result;
}
