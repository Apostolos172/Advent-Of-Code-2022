let fs = require("fs");
let data = fs.readFileSync("day14Small.txt", "utf8").split("\r\n");
data = fs.readFileSync("day14.txt", "utf8").split("\r\n");
//console.log(data);

let processedData = data.map((routeOfRock) => {
  return routeOfRock.split("->");
});
//console.log(processedData);

let minYDown = 0;
let maxYDown = Number.MIN_SAFE_INTEGER;
let maxXLeft = Number.MIN_SAFE_INTEGER;
let minXLeft = Number.MAX_SAFE_INTEGER;
processedData.forEach((routeOfRock) => {
  routeOfRock.forEach((coordinate) => {
    coordinate = coordinate.split(",");
    let x = parseInt(coordinate[0]);
    let y = parseInt(coordinate[1]);
    if (y > maxYDown) {
      maxYDown = y;
    }
    if (x > maxXLeft) {
      maxXLeft = x;
    }
    if (x < minXLeft) {
      minXLeft = x;
    }
  });
});

// minXLeft = parseInt(minXLeft);
// maxYDown = parseInt(maxYDown);
// maxXLeft = parseInt(maxXLeft);

//console.log(minXLeft, maxXLeft, maxYDown);

let cave = [];
for (let i = minYDown; i <= maxYDown; i++) {
  let tempArray = [];
  for (let j = minXLeft; j <= maxXLeft; j++) {
    //console.log(j)
    //tempArray.push(".")
    tempArray[j] = ".";
  }
  cave.push(tempArray);
}

//console.log(cave);
//console.log(cave[0][502])

processedData.forEach((routeOfRock) => {
  let previousCoordinate;
  for (let i = 0; i < routeOfRock.length; i++) {
    let coordinate = routeOfRock[i];
    if (i == 0) {
      previousCoordinate = coordinate;
    } else {
      coordinate = coordinate.split(",");
      let x = parseInt(coordinate[0].trim());
      let y = parseInt(coordinate[1].trim());
      previousCoordinate = previousCoordinate.split(",");
      let xPrevious = parseInt(previousCoordinate[0].trim());
      let yPrevious = parseInt(previousCoordinate[1].trim());
      if (xPrevious == x) {
        let min = Math.min(yPrevious, y);
        let max = Math.max(yPrevious, y);
        for (let j = min; j <= max; j++) {
          let other = x;
          cave[j][x] = "#";
        }
      } else if (yPrevious == y) {
        let min = Math.min(xPrevious, x);
        let max = Math.max(xPrevious, x);
        for (let j = min; j <= max; j++) {
          let other = y;
          cave[y][j] = "#";
          // console.log(y + " " + j + " " + cave[y][j])
          // console.log()
        }
      }
      // for (let j = xPrevious; j <= x; j++) {
      //   for (let k = yPrevious; k <= y; k++) {
      //     cave[k][j] = "#";
      //   }
      // }
      previousCoordinate = routeOfRock[i];
    }
  }
  // routeOfRock.forEach((coordinate) => {
  //   coordinate = coordinate.split(",");
  //   let x = parseInt(coordinate[0].trim());
  //   let y = parseInt(coordinate[1].trim());
  //   cave[y][x] = "#";
  // });
});

// source of sand
cave[0][500] = "+";
//console.log(cave);

//console.log(cave[9][494])

// part 1
let unitsOfSand = 0;

while (true) {
  // generate sand
  let x = 500;
  let y = 0;
  let abyss = false;
  while (true) {
    // sand comes to rest

    if (y + 1 > maxYDown || x + 1 > maxXLeft || x - 1 < minXLeft) {
      abyss = true;
      break;
    }
    if (cave[y + 1][x] == ".") {
      // one down
      y = y + 1;
    } else if (cave[y + 1][x - 1] == ".") {
      // one down and left
      y = y + 1;
      x = x - 1;
    } else if (cave[y + 1][x + 1] == ".") {
      // one down and right
      y = y + 1;
      x = x + 1;
    } else {
      // sand comes to rest
      unitsOfSand++;
      cave[y][x] = "o";
      break;
    }
  }
  if (abyss) {
    break;
  }
}

//console.log(cave[9][494])

console.log("Part 1: " + unitsOfSand);
//console.log("'"+minXLeft+"'")
//console.log(cave[9][minXLeft])

for(let i = 0; i < cave.length; i++) {
  let row = cave[i];
  let str="";
  for(let j = minXLeft; j <= maxXLeft; j++) {
    str=str+row[j];
  }
  //console.log(str);
}