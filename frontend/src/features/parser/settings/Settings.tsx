import { Button, FormControlLabel, Radio } from "@mui/material";
import Paper, { PaperProps } from "@mui/material/Paper";
import { FC } from "react";
import { Input, RadioGroup } from "../components";
import { BlackList } from "./BlackList";
const links = `mydearfriend3357:2431cf@5.9.244.199:10653|node-de-96.astroproxy.com:10653/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@135.125.164.133:10455|node-de-32.astroproxy.com:10455/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.196.77:10247|node-de-80.astroproxy.com:10247/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.196.76:10597|node-de-79.astroproxy.com:10597/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.130.108:10707|node-de-63.astroproxy.com:10707/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@141.95.21.251:10685|node-de-111.astroproxy.com:10685/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@51.195.37.10:10333|node-de-114.astroproxy.com:10333/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@51.89.125.18:10555|node-de-98.astroproxy.com:10555/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@135.125.164.134:10479|node-de-33.astroproxy.com:10479/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@51.77.79.243:10707|node-de-42.astroproxy.com:10707/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@54.37.194.34:10187|node-de-90.astroproxy.com:10187/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0
mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0`;

const keys = `IVDj8QEm54ZtZp7bDIUuNtXa01QK3xR4
3eCfhxD0rxgzT7LexyFdDi088Ui3gtDp
obVvdu0RVGLsJLSEYeLhOQnCk42vivWx
6FhKzdWnUq3ow45kU2OyjFxzfFopsapM
6AOOigSneRLQHgBnN5QTnu5MFHermCMg
L5PzPk5F7RFjE5svh2Xq252CVvRNzKX3
KHgwSgbpd3qgzbpQNoc6BDQAReIKCgzX
va1IuWFlr8UfMY9jEcagOjSl58IpCrMD
bKzoHTz8JBdTA6C7X8iQ9zqnAuiOt8nn
d57AHTTy8U7kfyx0te0dZHYlCIeP4apD
Ab9E6eGqxQz8Wq7YCSABwcE8APAWhDbI
sscyw9XE2oJ4NHZuNieY4B9rUJMFQBB6
SoXlcWw1TpuliyWOsLsjc3J9xs7FbNlQ
qzUgShjlff4q2SUB5eZO8yhQxAUnm9da
uaXj6IBziGUsUHFBoZrWKgmm9q4c4J6V
F2hHm5q6uhQ7rD29nEzZefSY7ZyaYXzT
sVGj49CAJTQB2DaVC0s2ynihCAJVd4lh
HjrWyFpoCbW87dIo3Q8MRjyMSEcX48ZY
slyNVDJnKnajKLOjR2qq8dEPwknnaDVS
LDgiBsychgMofArbPvzyLYBayWyaBv6e
UpaRzhtcwQ0WBPf1A9ywWAkuYhvfeFyJ
L2f8fXdYIxcmJqwZFB1e06gQnopGZZ46
facQpH0zuJbSGkuioAMP7PMgbK0VeVsq
hTgU8pC9zum8c3YwSXx0RJfAPw8ULjao
6tN65uGNt0zWaMk1dkcLQaAlRmHfRbvp
CTtyuLscxZUfuLZkrhFAFTS1pOs8FN8L
zom346n5MBPRwkXyfY5vuvHbvdC5dOww
8jTrl6XZjhCO0vAvpM6EF1XNTj0PdDWe
4ByVeHGSUYMhu1by1Ig8mCYDXewkm21U
FglZknGKakRxq2dUUj7ObXVv0A0bGu02
aKB4xIXCiOHm927vRKDzMWaAd2aEMjkW
3QlQq8r7AYlHtutWaSbUAX5QHJsimVYi
VeYNcU8BI0zW7PwT3o4bdu03fSFx3bJz
XFg5phCvHVFJZoYqYsZsbdVpFmBHK29Z
r5KLpAKS3Gi3Qkw4hV29Bjg2i1luTJFi
19m0ZUhkR2URS06nCLAzW29e0MJq20bQ
Z7bDXfvbSrMj17OjpT02L3jZC5ix3dgy
McJdDJtJ87xjqmZ4Mt56HEo5D5QuuXUg
xfBSqD1MFriQQW8fmS24N6qKCGo0w2PG
KpStBAa3xq5zEYeVkM6yuPWxHvEwRjBs
JjipoWexKXTOQ4znVE0y56ZIdgRJf1xt
d0n9WSUL3iy3AkmuXtJujJKOa0KCVEoU
MuqkT6TV85dqpbI3fe6Vk9k0ypgWXag8
QO5UQVgsyFfnBBJ36uNik9tgIAMDX9LB`;

export const Settings: FC<PaperProps> = ({ sx }) => {
  return (
    <Paper
      sx={{
        p: 1,
        columnGap: "2rem",
        gridTemplateColumns: "1.2fr 1fr",
        rowGap: "2rem",
        gridTemplateRows: "45vh 32vh 4.7vh",
        gridTemplateAreas: ` 
        "links       blackList"
        "keys        blackList"   
        "saveButton  blackList"`,
        ...sx,
      }}
    >
      <Input
        sx={{ gridArea: "links" }}
        label={"Ссылки-источники"}
        defaultValue={links}
        multiline
        // rows={20}
      />
      <Input
        sx={{ gridArea: "keys" }}
        label={"Ключи API от ipqualityscore.com"}
        defaultValue={keys}
        multiline
        // rows={10}
      />
      {/* ///////////////////////////////////////// */}
      {/* <Input
        sx={{ gridArea: "proxy" }}
        label={"Прокси для запросов"}
        value={"socks5://3211:6543211_country-us@geo.iproyal.com:12321"}
      />
      <Input
        sx={{ gridArea: "flowsNumber" }}
        label={"Количество потоков"}
        value={"3"}
      />
      <RadioGroup
        sx={{ gridArea: "proxyType" }}
        label={"Тип проксей"}
        value={"3"}
      >
        <FormControlLabel value="socks5" control={<Radio />} label="socks5" />
        <FormControlLabel value="http" control={<Radio />} label="http" />
      </RadioGroup> */}

      <Button variant="contained" sx={{ gridArea: "saveButton" }}>
        Сохранить
      </Button>
      <BlackList sx={{ gridArea: "blackList" }} />
    </Paper>
  );
};
