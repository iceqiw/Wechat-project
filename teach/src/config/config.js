const HOST = {
  local: 'http://localhost:9000',
  mock: 'http://localhost:9000',
  test: 'http://localhost:9000',
  prod: 'http://localhost:9000'
}

const CUSTOMER_API = ''

export const HOST_ADDRESS = HOST[process.env.NODE_ENV] || HOST.test
export const API_ADDRESS = HOST_ADDRESS + CUSTOMER_API
