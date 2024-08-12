const Redis = require("ioredis");
const mysql = require("mysql2");

const reids = new Redis({
    host: "localhost",
    port: 6379,
});

const pool = mysql.createPool({
    host: "localhost",
    user: "guo",
    password: "guo12345",
    database: "stylish",
});

const streamKey = "myStream";
const groupName = "consumerGroup";
const consumerName = "guo";

reids.xgroup("CREATE", streamKey, groupName, "$", "MKSTREAM", (err) => {
    if (err) {
        if (err.message.includes("BUSYGROUP")) {
            console.log("group already exists");
        } else {
            console.error("error creating group", err);
        }
    } else {
        console.log("group created");
    }
});

async function consumerStream() {
    while (true) {
        try {
            const messages = await reids.xreadgroup(
                "GROUP",
                groupName,
                consumerName,
                "BLOCK",
                300,
                "STREAMS",
                streamKey,
                ">"
            );

            if (messages) {
                messages.forEach(([stream, entries]) => {
                    entries.forEach(([id, fields]) => {
                        console.log(`Message ID: ${id}`);
                        let workType = Object.entries(fields)[1][1];
                        if (workType == "0") {
                            getReportWorker();
                            //ack message
                            reids.xack(streamKey, groupName, id);
                        }
                    });
                });
            }
        } catch (err) {
            console.error("Error reading stream:", err);
        }
    }
}

function getReportWorker() {
    pool.query("select * from user", (err, result) => {
        if (err) {
            console.error("error select data from user", err);
        } else {
            console.log(result);
        }
    });
}

consumerStream();
