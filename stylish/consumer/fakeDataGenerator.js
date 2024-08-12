const {faker} = require("@faker-js/faker");
const mysql = require("mysql2/promise");

const dbConfig = {
    host: "localhost",
    user: "guo",
    password: "guo12345",
    database: "stylish",
};

async function generateFakeData() {
    const connection = await mysql.createConnection(dbConfig);

    const recordCount = faker.number.int({min: 3000, max: 5000});

    for (let i = 0; i < recordCount; i++) {
        const userId = faker.number.int({min: 1, max: 5});
        const total = faker.number.int({min: 0, max: 1000, precision: 0.01});

        const query = "INSERT INTO report (user_id, total) VALUES (?, ?)";
        await connection.execute(query, [userId, total]);
    }

    console.log(`成功插入 ${recordCount} 筆資料`);

    await connection.end();
}

async function deleteAllData() {
    const connection = await mysql.createConnection(dbConfig);

    const query = "DELETE FROM report";
    const [result] = await connection.execute(query);

    console.log(`成功刪除 ${result.affectedRows} 筆資料`);

    await connection.end();
}

async function main() {
    const args = process.argv.slice(2);
    const action = args[0];

    if (action === "generate") {
        await generateFakeData();
    } else if (action === "delete") {
        await deleteAllData();
    } else {
        console.log("請指定操作：node script.js generate 或 node script.js delete");
    }
}

main().catch(console.error);
