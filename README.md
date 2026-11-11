# Territory Predictive Insights — CitizenTech

Sistema em Java que monitora **calor e energia** em um território, faz previsões simples e emite alertas explicando o motivo.

**Recorte escolhido:** 🌡 Calor e energia (ondas de calor e risco de sobrecarga na rede elétrica).

## Público beneficiado

- **Quem sofre o problema:** moradores de regiões com ondas de calor e risco de queda de energia.
- **Decisão que o sistema apoia:** a distribuidora ou a prefeitura decidir se reforça a rede, abre pontos de apoio contra o calor ou avisa a população.

> Nenhum dado pessoal é usado. O território é descrito apenas por região e equipamento.

---

## Estrutura

```
src/br/com/territoryPreditive/
├── main/
│   └── SistemaPrincipal.java        (menu e loop principal)
└── model/
    ├── ComponenteTerritorial.java   (classe abstrata)
    ├── EstacaoColeta.java           (herda de ComponenteTerritorial)
    ├── Sensor.java                  (interface)
    ├── SensorTemperatura.java       (implementa Sensor)
    ├── SensorUmidade.java           (implementa Sensor)
    ├── SensorEnergia.java           (implementa Sensor)
    ├── DadosTerritorio.java         (encapsulamento)
    ├── EstrategiaPrevisao.java      (classe abstrata)
    ├── PrevisaoMediaPonderada.java  (herda de EstrategiaPrevisao)
    ├── PrevisaoComparacao.java      (herda de EstrategiaPrevisao)
    ├── RespostaPrevisao.java        (resultado: dado previsto + motivos + status)
    ├── Status.java                  (enum: Normal, Atenção, Crítico)
    └── StatusSensor.java            (enum: Funcionando, Quebrado)
```

---

## Conceitos de POO

**Classe abstrata — `ComponenteTerritorial`**
Atributos `id`, `nome`, `regiao`, `status`. Métodos concretos `ativar()` e `desativar()`. Método abstrato `verificarStatus()`, implementado por `EstacaoColeta` (`extends`).

**Interface — `Sensor`**
Métodos `lerValor()`, `verificarStatus()` e `TipoSensor()`. Implementada (`implements`) por `SensorTemperatura`, `SensorUmidade` e `SensorEnergia`. Os valores são simulados com `Math.random()`:

| Sensor | Faixa simulada |
|---|---|
| Temperatura | 20 a 45 ºC |
| Umidade | 12 a 70 % |
| Energia | 1000 a 2000 |

**Encapsulamento — `DadosTerritorio`**
Atributos privados (`umidade`, `consumoEnergiaTotal`, `temperatura`) com getters e setters validados. Valor inválido é rejeitado com mensagem e o valor anterior é mantido.

Exemplo: `setUmidade(150)` mostra `[Error]: Não existe umidade maior que 100%` e a umidade não muda.

**Herança — `EstrategiaPrevisao`**
Classe abstrata com a lista de leituras (`ArrayList`) e o método abstrato `prever()`.
- `PrevisaoMediaPonderada`: média das leituras guardadas, chama `super()`.
- `PrevisaoComparacao`: compara a leitura atual com a anterior (atributo próprio `DadoAnterior`).

---

## Alertas

Cada previsão devolve um `RespostaPrevisao` com o dado previsto, a lista de motivos e o status (**Normal**, **Atenção** ou **Crítico**).

| Previsão | Regra | Nível |
|---|---|---|
| Média | Energia acima de 1500 | Atenção |
| Média | Energia acima de 1700 | Crítico |
| Média | Umidade abaixo de 50% | Atenção |
| Média | Umidade abaixo de 20% | Crítico |
| Média | Temperatura acima de 35 ºC | Atenção |
| Média | Temperatura acima de 45 ºC | Crítico |
| Comparação | Variação de energia de 200 ou mais | Atenção |
| Comparação | Variação de energia de 500 ou mais | Crítico |
| Comparação | Variação de temperatura acima de 5 ºC | Atenção |
| Comparação | Variação de temperatura acima de 15 ºC | Crítico |
| Comparação | Queda de umidade | Atenção / Crítico |

**Exemplo de saída:**
```
--- Media Ponderada ---
Umidade : 32.40%
Temperatura: 38.10ºC
Consumo de energia: 1620.00MW
Alertas:
[Alerta]: O uso de energia esta maior que 1500 GW/h
[Alerta]: Temperatura acima 35 graus, temperatura acima do normal
Status: Atenção
```

---

## Como executar

**Requisito:** Java JDK 21 ou superior.

**Menu:**
1. Ligar/Desligar a estação
2. Rodar os sensores
3. Fazer previsões sobre os dados
4. Info da estação
5. Sair

**Exemplo de uso:** digite `2` para ler os sensores, depois `3` para ver as previsões e os alertas. Na primeira rodada a Comparação avisa que falta dado anterior.

---

## Reflexão crítica

**Quando o sistema deve se calar?**
Com poucas leituras a previsão não é confiável. A Comparação já se cala na primeira rodada (sem leitura anterior). Um sensor quebrado também não deve gerar alerta.

**O que os dados não representam?**
Os valores são simulados, e uma estação por região diz pouco sobre um ponto específico, como um bairro sem sombra ou um transformador sobrecarregado.

**Por que o alerta apoia a decisão em vez de decidir?**
O sistema mostra o que foi medido e por quê. Quem conhece o território, como a prefeitura e a distribuidora, tem o contexto para decidir o que fazer.

---

## Uso de IA

- **Onde usamos:** [ex.: tirar dúvidas sobre `abstract` e `interface`].
- **Como revisamos:** [ex.: lemos o código, testamos cada classe e ajustamos o texto].
