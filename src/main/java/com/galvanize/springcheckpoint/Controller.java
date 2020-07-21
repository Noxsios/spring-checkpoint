package com.galvanize.springcheckpoint;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @GetMapping("/camelize")
    public String getCamelize(
            @RequestParam(value = "original", required = true, defaultValue = "") String original,
            @RequestParam(value = "initialCap", defaultValue = "false") boolean initialCap) {

        StringBuilder outString = new StringBuilder();
        String[] splitUnder = original.split("_");
        for (String str : splitUnder) {
            outString.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
        }

        if (!initialCap) {
            return outString.substring(0, 1).toLowerCase() + outString.substring(1);
        } else {
            return String.valueOf(outString);
        }

    }

    @GetMapping("/redact")
    public String getRedact(
            @RequestParam(value = "original", required = true, defaultValue = "") String original,
            @RequestParam(value = "badWord", defaultValue = "") List<String> badWords) {
        StringBuilder outString = new StringBuilder();
        String[] splitSpace = original.split(" ");

        for (String str : splitSpace) {
            for (String inner : badWords) {
                if (str.equals(inner)) {
                    int len = inner.length();
                    outString.append(" ").append("*".repeat(Math.max(0, len))).append(" ");
                } else {
                    outString.append(" ").append(str).append(" ");
                }
            }
        }
        return String.valueOf(outString).trim().replaceAll("\\s+", " ");
    }

    @PostMapping("/encode")
    public String postEncode(
            @RequestParam(value = "message", required = true, defaultValue = "") String message,
            @RequestParam(value = "key", defaultValue = "") String jumble
    ) {
        String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");
        StringBuilder outString = new StringBuilder();
        String[] splitAll = message.split("");
        for (int i = 0; i < splitAll.length; i++) {
            if (splitAll[i].equals(" ")) {
                outString.append(" ");
            } else {
                int index = Arrays.asList(alphabet).indexOf(splitAll[i]);
                String jumbledChar = jumble.split("")[index];
                outString.append(jumbledChar);
            }
        }
        return String.valueOf(outString);
    }

    @PostMapping("/s/{find}/{replace}")
    public String postSed(
            @PathVariable Map pathVariables,
            @RequestBody String rawBody
    ) {
        String find = (String) pathVariables.get("find");
        String replace = (String) pathVariables.get("replace");
        String[] splitSpace = rawBody.split(" ");
        StringBuilder outString = new StringBuilder();
        for (String str : splitSpace) {
            if (str.equals(find)) {
                outString.append(" ").append(replace).append(" ");
            } else {
                outString.append(" ").append(str).append(" ");
            }
        }
        return String.valueOf(outString);
    }

}
