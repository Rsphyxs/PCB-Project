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
        const allTodos = await client.query("SELECT * FROM pcbTable");
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

      const todo = await client.query(`INSERT INTO pcbTable (username, email, password) VALUES ('${username}', '${email}', '${password}')`);
      
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
    const password = request.body.password;
    console.log(username);
    console.log(email);
    console.log(password);
    if (username && email && password) {
        client.query('SELECT COUNT(username) FROM pcbTable WHERE username = $1 AND email = $2 AND password = $3', [username, email, password], function (error, results, fields) { 
          if (results.rows[0].count == 0) {
                console.log("Username dan password tidak terdaftar");
                response.jsonp({ success: false });
                //response.redirect('http://localhost:210/admin.html');
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
  
  app.put("/update/:username", async (req, res) => {
    try {
        const { id } = req.params;
        const todo = await client.query(`UPDATE pcbTable SET email = '${req.body.email}', password = '${req.body.password}' WHERE username= $1`, [
            username
        ]);
  
        res.json(todo.rows[0]);
    } catch (err) {
        console.error(err.message);
    }
  });

  app.post('/login', function (request, response) {
    var email = request.body.email;
    var password = request.body.password;
    console.log(email);
    console.log(password);
    if (email && password) {
        pool.query('SELECT COUNT(pcbTable) FROM pcb WHERE email = $1 AND password = $2', [email, password], function (error, results, fields) {
            if (results.rows[0].count == 0) {
                console.log("Login Gagal");
                request.session.loggedin = true;
                request.session.email = email;
                response.jsonp({ success: false });
                //response.redirect('http://localhost:210/admin.html');
            } else {
                console.log("Login berhasil");
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

  app.listen(port, () => {
    console.log(`Program sudah berjalan pada port ${port}`);
  });