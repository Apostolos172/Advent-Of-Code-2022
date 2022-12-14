//console.log("hello");

let fs = require("fs");
let data = fs.readFileSync("day13Small.txt", "utf8").split("\r\n");
//data = fs.readFileSync("day13.txt", "utf8").split("\r\n");
//console.log(data);

//console.log(data);
let firstArray = JSON.parse(data[3]);
//console.log("="+data[2]+"=");
//console.log(firstArray);
//console.log(firstArray[1][1]);
let packets = data.map((item) => {
  if (item !== "") {
    return JSON.parse(item);
  } else {
    return undefined;
  }
});
console.log(data);
//console.log(packets);

let pairsOfPackets = [];
index = 0;
for (let i = 0; i < packets.length; i = i + 3) {
  pairsOfPackets[index] = [packets[i], packets[i + 1]];
  index++;
}
console.log(pairsOfPackets);

const compareTwoPackets = (correct, iOfPacketsPairs, sum, firstPacket, secondPacket) => {
  // firstPacket.forEach((value, index) => {
  //     if(typeof value == 'number') {
  //         if(Array.isArray(secondPacket[index]) ) {
  //             compareTwoPackets([value], secondPacket[index])
  //             continue;
  //         }
  //     } else {

  //     }
  // });

  //console.log(firstPacket)
  for (let i = 0; i < firstPacket.length; i++) {
    let value = firstPacket[i];
    let index = i;
    //console.log( typeof value)
    //console.log( typeof value == "number")
    if (typeof value == "number") {
      //   if (!secondPacket[index]) {
      //     break;
      //   }
      if (!secondPacket) {
        //correct = false;
        break;
      }
      if (Array.isArray(secondPacket[index])) {
        [sum, correct] = compareTwoPackets( correct,
          iOfPacketsPairs,
          sum,
          [value],
          secondPacket[index]
        );
        //continue;
        break;
      }

      // if (i == firstPacket.length - 1) {
      //   if (value <= secondPacket[index]) {
      //     //sum += iOfPacketsPairs;
      //   }
      // }
      if (value < secondPacket[index]) {
        sum += iOfPacketsPairs;
        break;
        //continue;
      } else if (value > secondPacket[index]) {
        correct = false;
        break;
      }
    } else {
      // firstPacket element is Array
      //console.log(typeof secondPacket[index] == "number")
      if (!secondPacket) {
        //correct = false;
        break;
      }
      if (typeof secondPacket[index] == "number") {
        [sum, correct] = compareTwoPackets(correct, iOfPacketsPairs, sum, firstPacket[index], [
          secondPacket[index],
        ]);
        //continue;
        break;
      }
      //console.log(firstPacket[index])
      [sum, correct] = compareTwoPackets(correct,
        iOfPacketsPairs,
        sum,
        firstPacket[index],
        secondPacket[index]
      );
      //break;
      //continue;
    }
  }
  return [sum, correct];
};

let sum = 0;
//console.log(pairsOfPackets.length)
for (let i = 0; i < pairsOfPackets.length; i++) {
  let correct = true;
  //console.log(pairsOfPackets[i][0])
  //console.log(i + ": " + pairsOfPackets[i][0])
  prevSum = sum;
  [sum, correct] = compareTwoPackets( correct,
    i + 1,
    sum,
    pairsOfPackets[i][0],
    pairsOfPackets[i][1]
  );
  //if (sum == prevSum) {
  if (correct && sum == prevSum) {
    console.log(pairsOfPackets[i][1].length)
    console.log(pairsOfPackets[i][0].length)
    //if (pairsOfPackets[i][1].length > pairsOfPackets[i][0].length) {
    if (pairsOfPackets[i][1].length != pairsOfPackets[i][0].length) {
      console.log(pairsOfPackets[i][0].length)
      if(pairsOfPackets[i][0].length!=0) {
        sum += i + 1;
      }
    }
  }
}
console.log(sum);
