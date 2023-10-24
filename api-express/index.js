const express = require("express");
const app = express();

app.listen(4000, function(erro) {
    if(erro) console.log("ocorreu um erro") 
    else console.log("servidor iniciado com sucesso")
});

app.post("/calcula-juros", (req, res) => {
    console.log(req);
    res.send({
        "data": {
          "percentual_juros": 1.93,
          "valor_total": 52000.00
        }
      })
});
