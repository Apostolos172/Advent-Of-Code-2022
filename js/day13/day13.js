let fs = require("fs");
let data = fs.readFileSync("day13Small.txt", "utf8").split("\r\n");
data = fs.readFileSync("day13.txt", "utf8").split("\r\n");

let packets = data.map((item) => {
  if (item !== "") {
    return JSON.parse(item);
  } else {
    return undefined;
  }
});
//console.log(data);
//console.log(packets);

let pairsOfPackets = [];
index = 0;
for (let i = 0; i < packets.length; i = i + 3) {
  pairsOfPackets[index] = [packets[i], packets[i + 1]];
  index++;
}
//console.log(pairsOfPackets);

const areTwoPacketsInRightOrder = (firstPacket, secondPacket) => {
  let index = 0;
  while (index < firstPacket.length && index < secondPacket.length) {
    let correct;
    value1 = firstPacket[index];
    value2 = secondPacket[index];
    if (typeof value1 == "number" && typeof value2 == "number") {
      if (value1 < value2) {
        return true;
      }
      if (value1 > value2) {
        return false;
      }
    } else if (typeof value1 == "number") {
      correct = areTwoPacketsInRightOrder([value1], value2);
      if (typeof correct == "boolean") {
        return correct;
      }
    } else if (typeof value2 == "number") {
      correct = areTwoPacketsInRightOrder(value1, [value2]);
      if (typeof correct == "boolean") {
        return correct;
      }
    } else {
      correct = areTwoPacketsInRightOrder(value1, value2);
      if (typeof correct == "boolean") {
        return correct;
      }
    }
    index++;
  }
  if (index < firstPacket.length) {
    return false;
  }
  if (index < secondPacket.length) {
    return true;
  }
};

let sum = 0;
for (let i = 0; i < pairsOfPackets.length; i++) {
  let correct = areTwoPacketsInRightOrder(
    pairsOfPackets[i][0],
    pairsOfPackets[i][1]
  );
  if (correct) {
    sum += i + 1;
  }
}
console.log("Part 1: " + sum);

// part 2
let newPairsOfPackets = [...pairsOfPackets, [[[2]], [[6]]]]
let orderedPackets = [];
let key = 1;
for (let i = 0; i < newPairsOfPackets.length; i++) {
  let pair = newPairsOfPackets[i];
  if (i == 0) {
    orderedPackets.push(pair[0]);
    pair = [pair[1]];
  }
  for (let j = 0; j < pair.length; j++) {
    let index = orderedPackets.length;
    for (let k = 0; k < orderedPackets.length; k++) {
      if(areTwoPacketsInRightOrder(pair[j],orderedPackets[k])){
        index = k
        break
      }
    }
    orderedPackets.splice(index,0,pair[j])
    if(i>=pairsOfPackets.length) {
      key *= index+1
    }
  }
}
console.log("Part 2: key: " + key)
