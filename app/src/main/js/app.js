const http = require('http');
const express = require('express');
const app = express();
const bodyParser = require('body-parser');
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
const { Client } = require('pg');
const { response } = require('express');
const cors = require('cors');
app.use(cors());
const port = process.env.PORT || 210;

const client = new Client({
    // Lengkapi koneksi dengan database
    host: "localhost",
    port: 5432,
    user: "postgres",
    password: "",
    database: "pcb_project",
  });

  client.connect((err) =>{
    if (err) {
        console.error(err);
        return;
    }
    console.log('Database Connected');
  });
  
  app.get("/show", async (req, res) => {
    try {
        const allTodos = await client.query("SELECT * FROM pcbTable2");
        res.json(allTodos.rows);
    } catch (err) {
        console.error(err.message);
    }
  });

  app.post('/add', async(req, res) => {
    try {
      const username = req.body.username;
      const email  = req.body.email;
      const password  = req.body.password;

      const todo = await client.query(`INSERT INTO pcbTable2 (username, email, password) VALUES ('${username}', '${email}', '${password}')`);
      
      console.log("Berhasil di insert bro");
      res.json({status:'success'});
    } catch (err) {
      // console.log(req.body);
      console.error(err.message);
      console.log("Ga masuk cok");
      res.json({status:'fail'});
    }
  });

  app.post('/verif', function (request, response) {
    const username = request.body.username;
    const email = request.body.email;
    console.log(username);
    console.log(email);
    if (username && email) {
        client.query('SELECT COUNT(username) FROM pcbTable2 WHERE username = $1 OR email = $2', [username, email], function (error, results, fields) { 
          if (results.rows[0].count == 0) {
                console.log("Username dan password tidak terdaftar");
                response.jsonp({ success: false });
            } else {
                console.log("Username dan password terdaftar");
                response.jsonp({ success: true });
            }
            response.end();
        });
    } else {
        console.log("Ga masuk");
        response.send('Please enter Username and Password!');
        response.end();
    }
  });

  app.post('/login', function (request, response) {
    const password = request.body.password;
    const email = request.body.email;
    console.log("email: " + email);
    console.log("password: " + password);
    if (email && password) {
        client.query('SELECT COUNT(username) FROM pcbTable2 WHERE email = $1 AND password = $2', [email, password], function (error, results, fields) { 
          if (results.rows[0].count == 0) {
                console.log("Username dan password tidak terdaftar");
                response.jsonp({ success: false });
            } else {
                console.log("Username dan password terdaftar");
                response.jsonp({ success: true });
            }
            response.end();
        });
    } else {
        console.log("Ga masuk");
        response.send('Please enter Username and Password!');
        response.end();
    }
  });
  
  app.post('/username', async(req, res) => {
    try {
      const email  = req.body.email;

      const todo = await client.query(`select * from pcbTable2 where email = '${email}'`);
      
      console.log("Berhasil di show bro");
      // console.log(todo.rows[0].username);
      // res.json({username: todo.rows[0].username});
      console.log(todo.rows);
      res.json(todo.rows);
    } catch (err) {
      // console.log(req.body);
      console.error(err.message);
      console.log("Ga masuk cok");
      res.json({status:'fail'});
    }
  });

  app.post("/cpu", async (req, res) => {
    try {
        const todo = await client.query("SELECT * FROM CPU");
        console.log(todo.rows);
        res.json(todo.rows);
    } catch (err) {
        console.error(err.message);
    }
  });

  app.listen(port, () => {
    console.log(`Program sudah berjalan pada port ${port}`);
  });