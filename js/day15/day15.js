const DEBUG = false;
//const SIZE = 30;
const SIZE = 6000000;
//const addedSpace = 6;
const addedSpace = 450000
let yAsked = 10; // example input
yAsked = 2000000; // part 1
//4757767 // too low
// 5100463 ✔

let fs = require("fs");
let data = fs.readFileSync("day15Small.txt", "utf8").split("\r\n");
data = fs.readFileSync("day15.txt", "utf8").split("\r\n");
//console.log(data);

class Coordinate {
  constructor(x, y) {
    this.x = x;
    this.y = y;
  }
}

const getDistance = (pointA, pointB) => {
  // Manhattan distance
  return Math.abs(pointA.x - pointB.x) + Math.abs(pointA.y - pointB.y);
};

let processedData = data.map((pairOfSensorBeacon) => {
  let pair = pairOfSensorBeacon
    .replace("Sensor at", "")
    .replace("closest beacon is at", "")
    .split(":")
    .map((point, index) => {
      point = point.trim().split(",");
      //console.log(point);
      //console.log(point[0].split("="))
      return new Coordinate(
        parseInt(point[0].split("=")[1]),
        parseInt(point[1].trim().split("=")[1])
      );
    });
  //console.log(pair);
  return pair;
});
if (DEBUG) {
  console.log(processedData);
}

let cave = [];
//const SIZE = 30;
for (let i = 0; i <= SIZE; i++) {
  let tempArray = [];
  // for (let j = 0; j <= SIZE; j++) {
  //   tempArray[j] = ".";
  // }
  cave.push(tempArray);
}
//console.log(cave);

//const addedSpace = 6;
processedData.forEach((pair) => {
  cave[pair[0].y + addedSpace][pair[0].x + addedSpace] = "S";
  cave[pair[1].y + addedSpace][pair[1].x + addedSpace] = "B";
});

if (DEBUG) {
  for (let i = 0; i <= SIZE; i++) {
    let str = "";
    for (let j = 0; j <= SIZE; j++) {
      str = str + cave[i][j] + " ";
    }
    console.log(str);
  }
}

processedData.forEach((pairOfSensorBeacon) => {
  let sensor = new Coordinate(pairOfSensorBeacon[0].x, pairOfSensorBeacon[0].y);
  let beacon = new Coordinate(pairOfSensorBeacon[1].x, pairOfSensorBeacon[1].y);
  let distance = getDistance(sensor, beacon);

  let y = yAsked + addedSpace;

  //for (let i = 0; i <= SIZE; i++) {
  for (let i = y; i <= y; i++) {
    for (let j = 0; j <= SIZE; j++) {
      let currentPoint = new Coordinate(j - addedSpace, i - addedSpace);
      if (getDistance(currentPoint, sensor) <= distance) {
        if (cave[i][j] != "B" && cave[i][j] != "S") {
          cave[i][j] = "#";
        }
      }
    }
  }
});

if (DEBUG) {
  console.log("");
  for (let i = 0; i <= SIZE; i++) {
    let str = "";
    for (let j = 0; j <= SIZE; j++) {
      str = str + cave[i][j] + " ";
    }
    console.log(str);
  }
}

const getPositionsCannotContainABeacon = (y) => {
  //ForAsensorRow not necessary
  let sum = 0;
  y = y + addedSpace;
  for (let i = 0; i <= SIZE; i++) {
    if (cave[y][i] === "#") {
      sum++;
    }
  }
  return sum;
};

// part 1
//y = yAsked;
console.log("Part 1: " + getPositionsCannotContainABeacon(yAsked));

// let processedData = data.map((routeOfRock) => {
//   return routeOfRock.split("->");
// });
// //console.log(processedData);

// let minYDown = 0;
// let maxYDown = Number.MIN_SAFE_INTEGER;
// let maxXLeft = Number.MIN_SAFE_INTEGER;
// let minXLeft = Number.MAX_SAFE_INTEGER;
// processedData.forEach((routeOfRock) => {
//   routeOfRock.forEach((coordinate) => {
//     coordinate = coordinate.split(",");
//     let x = parseInt(coordinate[0]);
//     let y = parseInt(coordinate[1]);
//     if (y > maxYDown) {
//       maxYDown = y;
//     }
//     if (x > maxXLeft) {
//       maxXLeft = x;
//     }
//     if (x < minXLeft) {
//       minXLeft = x;
//     }
//   });
// });

// let cave = [];
// for (let i = minYDown; i <= maxYDown; i++) {
//   let tempArray = [];
//   for (let j = 0; j <= maxXLeft; j++) {
//     tempArray[j] = ".";
//   }
//   cave.push(tempArray);
// }
// //console.log(cave);

// processedData.forEach((routeOfRock) => {
//   let previousCoordinate;
//   for (let i = 0; i < routeOfRock.length; i++) {
//     let coordinate = routeOfRock[i];
//     if (i == 0) {
//       previousCoordinate = coordinate;
//     } else {
//       coordinate = coordinate.split(",");
//       let x = parseInt(coordinate[0].trim());
//       let y = parseInt(coordinate[1].trim());
//       previousCoordinate = previousCoordinate.split(",");
//       let xPrevious = parseInt(previousCoordinate[0].trim());
//       let yPrevious = parseInt(previousCoordinate[1].trim());
//       if (xPrevious == x) {
//         let min = Math.min(yPrevious, y);
//         let max = Math.max(yPrevious, y);
//         for (let j = min; j <= max; j++) {
//           cave[j][x] = "#";
//         }
//       } else if (yPrevious == y) {
//         let min = Math.min(xPrevious, x);
//         let max = Math.max(xPrevious, x);
//         for (let j = min; j <= max; j++) {
//           cave[y][j] = "#";
//         }
//       }
//       previousCoordinate = routeOfRock[i];
//     }
//   }
// });

// // source of sand
// cave[0][500] = "+";

// let initialCave = [...cave];

// // part 2

// let finalRow1000 = [];
// let row1000 = [];
// const addedSpace = 1000;
// for (let j = 0; j < addedSpace; j++) {
//   row1000[j] = ".";
//   finalRow1000[j] = "#";
// }
// let finalRow = [];
// let previousRow = [];
// for (let j = 0; j <= maxXLeft; j++) {
//   previousRow[j] = ".";
//   finalRow[j] = "#";
// }
// initialCave.push(previousRow);
// initialCave.push(finalRow);

// let yOfFloor = maxYDown + 2;
// let newCave = [];
// for (let i = 0; i <= yOfFloor; i++) {
//   if (i == yOfFloor) {
//     newCave.push([...finalRow1000, ...initialCave[i], ...finalRow1000]);
//     continue;
//   }
//   newCave.push([...row1000, ...initialCave[i], ...row1000]);
// }
// initialCave = [...newCave];

// const fiveHundred = addedSpace + 500;
// initialCave[0][fiveHundred] = ".";
// let unitsSand = 0;

// while (true) {
//   // generate sand
//   let x = fiveHundred;
//   //let y = 0;
//   let y = -1;
//   let abyss = false;
//   let full = false;
//   while (true) {
//     // sand comes to rest

//     if (initialCave[0][fiveHundred] != ".") {
//       full = true;
//       break;
//     }
//     if (initialCave[y + 1][x] == ".") {
//       // one down
//       y = y + 1;
//     } else if (initialCave[y + 1][x - 1] == ".") {
//       // one down and left
//       y = y + 1;
//       x = x - 1;
//     } else if (initialCave[y + 1][x + 1] == ".") {
//       // one down and right
//       y = y + 1;
//       x = x + 1;
//     } else {
//       // sand comes to rest
//       unitsSand++;
//       initialCave[y][x] = "o";
//       break;
//     }
//   }
//   if (full) {
//     break;
//   }
// }

// console.log("Part 2: " + unitsSand);

// // part 1
// let unitsOfSand = 0;

// while (true) {
//   // generate sand
//   let x = 500;
//   let y = 0;
//   let abyss = false;
//   while (true) {
//     // sand comes to rest

//     if (y + 1 > maxYDown || x + 1 > maxXLeft || x - 1 < minXLeft) {
//       abyss = true;
//       break;
//     }
//     if (cave[y + 1][x] == ".") {
//       // one down
//       y = y + 1;
//     } else if (cave[y + 1][x - 1] == ".") {
//       // one down and left
//       y = y + 1;
//       x = x - 1;
//     } else if (cave[y + 1][x + 1] == ".") {
//       // one down and right
//       y = y + 1;
//       x = x + 1;
//     } else {
//       // sand comes to rest
//       unitsOfSand++;
//       cave[y][x] = "o";
//       break;
//     }
//   }
//   if (abyss) {
//     break;
//   }
// }

// console.log("Part 1: " + unitsOfSand);
