import http from 'k6/http';
import { check } from 'k6';

export const options = {
  scenarios: {
    payment_load: {
      executor: 'constant-arrival-rate',
      rate: 150,          // 150 requests every second
      timeUnit: '1s',
      duration: '5m',
      preAllocatedVUs: 100,
      maxVUs: 200
    }
  }
};

export default function () {

    const txnId =
        "TXN-" +
        Date.now() +
        "-" +
        Math.floor(Math.random() * 1000000);

    const payload = JSON.stringify({
        transactionId: txnId,
        senderId: 101,
        receiverId: 102,
        amount: 1,
        currency: "INR"
    });

    const res = http.post(
        "http://localhost:8080/v1/payments",
        payload,
        {
            headers: {
                "Content-Type": "application/json"
            }
        }
    );

    let body = {};

    try {
        body = JSON.parse(res.body);
    } catch (e) {}

    check(res, {
        "HTTP 202": (r) => r.status === 202,
        "Response < 500 ms": (r) => r.timings.duration < 500,
        "paymentId returned": () => body.paymentId !== undefined,
        "status is PENDING": () => body.status === "PENDING"
    });
}