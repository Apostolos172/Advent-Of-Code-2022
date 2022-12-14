const DEBUG = false;
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
//console.log("maxYDown: " + maxYDown)

let cave = [];
//for (let i = minYDown; i <= maxYDown; i++) {
for (let i = 0; i <= maxYDown; i++) {
  let tempArray = [];
  //for (let j = minXLeft; j <= maxXLeft; j++) {
  for (let j = 0; j <= maxXLeft; j++) {
    tempArray[j] = ".";
  }
  cave.push(tempArray);
}
//console.log(cave);

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
        }
      }
      //console.log(x);
      previousCoordinate = routeOfRock[i];
    }
  }
});

// source of sand
cave[0][500] = "+";

// part 1
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

// see the output as image
if (DEBUG) {
  for (let i = 0; i < cave.length; i++) {
    let row = cave[i];
    let str = "";
    for (let j = minXLeft; j <= maxXLeft; j++) {
      //for (let j = 0; j <= row.length; j++) {
      str = str + row[j];
    }
    console.log(str);
  }
}

// part 2
let yOfFloor = maxYDown + 2;
//console.log(yOfFloor);

// let additionalcave = [];
// for (let i = 0; i <= yOfFloor; i++) {
//   //for (let i = 0; i <= maxYDown; i++) {
//   let tempArray = [];
//   for (let j = minXLeft; j <= maxXLeft; j++) {
//     //for (let j = 0; j <= maxXLeft; j++) {
//     tempArray[j] = ".";
//   }
//   cave.push(tempArray);
// }

let finalRow1000 = [];
let row1000 = [];
let add = 1000;
for (let j = 0; j < add; j++) {
  row1000[j] = ".";
  finalRow1000[j] = "#";
}
let finalRow = [];
let previousRow = [];
//for (let j = minXLeft; j <= maxXLeft; j++) {
for (let j = 0; j <= maxXLeft; j++) {
  previousRow[j] = ".";
  finalRow[j] = "#";
}
cave.push(previousRow);
cave.push(finalRow);

let additionalcave = [];
for (let i = 0; i <= yOfFloor; i++) {
  if (i == yOfFloor) {
    let tempArray = [...finalRow1000, ...cave[i], ...finalRow1000];
    additionalcave.push(tempArray);
    continue;
  }
  let tempArray = [...row1000, ...cave[i], ...row1000];
  additionalcave.push(tempArray);
}

let newCave = [...additionalcave];
//console.log(newCave);
cave = [...newCave];

//const fiveHundred = 500;
const fiveHundred = add + 500;
cave[0][fiveHundred] = ".";
unitsOfSand = 0;

while (true) {
  // generate sand
  let x = fiveHundred;
  //let y = 0;
  let y = -1;
  let abyss = false;
  let full = false;
  while (true) {
    // sand comes to rest

    //if (cave[0][fiveHundred] != "+") {
    if (cave[0][fiveHundred] != ".") {
      full = true;
      break;
    }
    // if (y + 1 > maxYDown || x + 1 > maxXLeft || x - 1 < minXLeft) {
    //   abyss = true;
    //   break;
    // }
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
  if (full) {
    break;
  }
}

console.log("Part 2: " + unitsOfSand);
if (DEBUG) {
  for (let i = 0; i < cave.length; i++) {
    let row = cave[i];
    let str = "";
    for (let j = minXLeft; j <= maxXLeft +add; j++) {
      //for (let j = 0; j <= row.length; j++) {
      str = str + row[j];
    }
    console.log(str);
  }
}
