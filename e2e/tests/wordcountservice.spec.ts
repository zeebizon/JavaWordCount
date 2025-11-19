import { test, expect } from "@playwright/test";

test.describe("WordCountService", () => {
    test("/words/most-common/frequency/", async ({ request }) => {

        var body = { text: "The quick brown fox jumps over the lazy dog. The dog barks." };
        var res = await request.post("/words/most-common/frequency/", { data: body });

        expect(res.status()).toBe(200);
        var responseBody = await res.json();

        expect(responseBody).toEqual({ frequency: 3 });
    });

    test("/words/{word}/frequency/", async ({ request }) => {
        var body = { text: "The quick brown fox jumps over the lazy dog. The dog barks." };
        var word = "the";

        var res = await request.post(`/words/${word}/frequency/`, { data: body });

        expect(res.status()).toBe(200);
        var responseBody = await res.json();

        expect(responseBody).toEqual({ word: "the", frequency: 3 });
    });

    test("/words/most-common/{}n}", async ({ request }) => {
        var body = { text: "The quick brown fox jumps over the lazy dog. The dog barks." };
        var n = 3;

        var res = await request.post(`/words/most-common/${n}`, { data: body });
        expect(res.status()).toBe(200);
        var responseBody = await res.json();

        expect(responseBody).toEqual({
            wordFrequencies: [
                { word: "the", frequency: 3 },
                { word: "dog", frequency: 2 },
                { word: "barks", frequency: 1 }
            ]
        });
    });

});
