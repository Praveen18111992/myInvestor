const util = require('util');

module.exports = {
    fields: {
        category: { type: 'text' },
        exchange_name: { type: 'text' },
        stock_symbol: { type: 'text' },
        createDate: { type: 'timestamp' }
    },
    key: [
        ["category"], 'exchange_name', 'stock_symbol'
    ],
    table_name: "chosen_stock"
};