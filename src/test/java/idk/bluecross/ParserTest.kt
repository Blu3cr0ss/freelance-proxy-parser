package idk.bluecross

import org.junit.jupiter.api.Test

class ParserTest {
//    @Test
//    fun test() {
//        val p = Parser(
//            "mydearfriend3357:2431cf@5.9.244.199:10653|node-de-96.astroproxy.com:10653/api/changeIP?apiToken=49fbea178cdf4dc0\n" +
//                    "mydearfriend3357:2431cf@135.125.164.133:10455|node-de-32.astroproxy.com:10455/api/changeIP?apiToken=49fbea178cdf4dc0\n" +
//                    "mydearfriend3357:2431cf@162.19.196.77:10247|node-de-80.astroproxy.com:10247/api/changeIP?apiToken=49fbea178cdf4dc0\n" +
//                    "mydearfriend3357:2431cf@162.19.196.76:10597|node-de-79.astroproxy.com:10597/api/changeIP?apiToken=49fbea178cdf4dc0\n" +
//                    "mydearfriend3357:2431cf@162.19.130.108:10707|node-de-63.astroproxy.com:10707/api/changeIP?apiToken=49fbea178cdf4dc0\n" +
//                    "mydearfriend3357:2431cf@141.95.21.251:10685|node-de-111.astroproxy.com:10685/api/changeIP?apiToken=49fbea178cdf4dc0\n" +
//                    "mydearfriend3357:2431cf@51.195.37.10:10333|node-de-114.astroproxy.com:10333/api/changeIP?apiToken=49fbea178cdf4dc0\n" +
//                    "mydearfriend3357:2431cf@51.89.125.18:10555|node-de-98.astroproxy.com:10555/api/changeIP?apiToken=49fbea178cdf4dc0\n" +
//                    "mydearfriend3357:2431cf@135.125.164.134:10479|node-de-33.astroproxy.com:10479/api/changeIP?apiToken=49fbea178cdf4dc0\n" +
//                    "mydearfriend3357:2431cf@51.77.79.243:10707|node-de-42.astroproxy.com:10707/api/changeIP?apiToken=49fbea178cdf4dc0\n" +
//                    "mydearfriend3357:2431cf@54.37.194.34:10187|node-de-90.astroproxy.com:10187/api/changeIP?apiToken=49fbea178cdf4dc0\n" +
//                    "mydearfriend3357:2431cf@162.19.146.89:10099|node-de-25.astroproxy.com:10099/api/changeIP?apiToken=49fbea178cdf4dc0",
//            ApiKeysList(
//                "IVDj8QEm54ZtZp7bDIUuNtXa01QK3xR4\n" +
//                        "3eCfhxD0rxgzT7LexyFdDi088Ui3gtDp\n" +
//                        "obVvdu0RVGLsJLSEYeLhOQnCk42vivWx\n" +
//                        "6FhKzdWnUq3ow45kU2OyjFxzfFopsapM\n" +
//                        "6AOOigSneRLQHgBnN5QTnu5MFHermCMg\n" +
//                        "L5PzPk5F7RFjE5svh2Xq252CVvRNzKX3\n" +
//                        "KHgwSgbpd3qgzbpQNoc6BDQAReIKCgzX\n" +
//                        "va1IuWFlr8UfMY9jEcagOjSl58IpCrMD\n" +
//                        "bKzoHTz8JBdTA6C7X8iQ9zqnAuiOt8nn\n" +
//                        "d57AHTTy8U7kfyx0te0dZHYlCIeP4apD\n" +
//                        "Ab9E6eGqxQz8Wq7YCSABwcE8APAWhDbI\n" +
//                        "sscyw9XE2oJ4NHZuNieY4B9rUJMFQBB6\n" +
//                        "SoXlcWw1TpuliyWOsLsjc3J9xs7FbNlQ\n" +
//                        "qzUgShjlff4q2SUB5eZO8yhQxAUnm9da\n" +
//                        "uaXj6IBziGUsUHFBoZrWKgmm9q4c4J6V\n" +
//                        "F2hHm5q6uhQ7rD29nEzZefSY7ZyaYXzT\n" +
//                        "sVGj49CAJTQB2DaVC0s2ynihCAJVd4lh\n" +
//                        "HjrWyFpoCbW87dIo3Q8MRjyMSEcX48ZY\n" +
//                        "slyNVDJnKnajKLOjR2qq8dEPwknnaDVS\n" +
//                        "LDgiBsychgMofArbPvzyLYBayWyaBv6e\n" +
//                        "UpaRzhtcwQ0WBPf1A9ywWAkuYhvfeFyJ\n" +
//                        "L2f8fXdYIxcmJqwZFB1e06gQnopGZZ46\n" +
//                        "facQpH0zuJbSGkuioAMP7PMgbK0VeVsq\n" +
//                        "hTgU8pC9zum8c3YwSXx0RJfAPw8ULjao\n" +
//                        "6tN65uGNt0zWaMk1dkcLQaAlRmHfRbvp\n" +
//                        "CTtyuLscxZUfuLZkrhFAFTS1pOs8FN8L\n" +
//                        "zom346n5MBPRwkXyfY5vuvHbvdC5dOww\n" +
//                        "8jTrl6XZjhCO0vAvpM6EF1XNTj0PdDWe\n" +
//                        "4ByVeHGSUYMhu1by1Ig8mCYDXewkm21U\n" +
//                        "FglZknGKakRxq2dUUj7ObXVv0A0bGu02\n" +
//                        "aKB4xIXCiOHm927vRKDzMWaAd2aEMjkW\n" +
//                        "3QlQq8r7AYlHtutWaSbUAX5QHJsimVYi\n" +
//                        "VeYNcU8BI0zW7PwT3o4bdu03fSFx3bJz\n" +
//                        "XFg5phCvHVFJZoYqYsZsbdVpFmBHK29Z\n" +
//                        "r5KLpAKS3Gi3Qkw4hV29Bjg2i1luTJFi\n" +
//                        "19m0ZUhkR2URS06nCLAzW29e0MJq20bQ\n" +
//                        "Z7bDXfvbSrMj17OjpT02L3jZC5ix3dgy\n" +
//                        "McJdDJtJ87xjqmZ4Mt56HEo5D5QuuXUg\n" +
//                        "xfBSqD1MFriQQW8fmS24N6qKCGo0w2PG\n" +
//                        "KpStBAa3xq5zEYeVkM6yuPWxHvEwRjBs\n" +
//                        "JjipoWexKXTOQ4znVE0y56ZIdgRJf1xt\n" +
//                        "d0n9WSUL3iy3AkmuXtJujJKOa0KCVEoU\n" +
//                        "MuqkT6TV85dqpbI3fe6Vk9k0ypgWXag8\n" +
//                        "QO5UQVgsyFfnBBJ36uNik9tgIAMDX9LB"
//            ),
//            listOf(
//                BlackList(
//                    listOf(
//                        "\n" +
//                                "89.247.124.176\n" +
//                                "77.0.80.77\n" +
//                                "46.114.59.196\n" +
//                                "87.189.255.116\n" +
//                                "94.216.187.192\n" +
//                                "95.112.195.171\n" +
//                                "79.198.132.83\n" +
//                                "37.201.224.53\n" +
//                                "188.108.24.210\n" +
//                                "217.253.98.19\n" +
//                                "80.143.206.110\n" +
//                                "37.201.153.141\n" +
//                                "93.222.252.228\n" +
//                                "37.201.185.49\n" +
//                                "84.140.108.185\n" +
//                                "93.243.232.155\n" +
//                                "84.164.160.47\n" +
//                                "79.192.79.184\n" +
//                                "77.8.88.117\n" +
//                                "217.224.72.246\n" +
//                                "79.218.150.193\n" +
//                                "178.8.241.222\n" +
//                                "46.5.17.48\n" +
//                                "87.182.196.23\n" +
//                                "109.192.194.255\n" +
//                                "62.216.206.160\n" +
//                                "88.130.99.57\n" +
//                                "46.142.181.46\n" +
//                                "95.112.245.121\n" +
//                                "89.12.118.110\n" +
//                                "5.146.84.26\n" +
//                                "91.3.250.230\n" +
//                                "37.209.83.66\n" +
//                                "178.24.237.232\n" +
//                                "93.239.39.127\n" +
//                                "77.22.87.147\n" +
//                                "41.216.188.157\n" +
//                                "77.0.156.166\n" +
//                                "31.150.49.242\n" +
//                                "91.51.136.139\n" +
//                                "84.159.212.99\n" +
//                                "84.154.223.206\n" +
//                                "79.217.175.1\n" +
//                                "77.189.168.83\n" +
//                                "79.241.246.127\n" +
//                                "31.150.18.92\n" +
//                                "87.154.5.17\n" +
//                                "79.225.226.18\n" +
//                                "91.96.65.226\n" +
//                                "84.149.134.10\n" +
//                                "93.213.121.45\n" +
//                                "37.201.224.7\n" +
//                                "79.214.32.205\n" +
//                                "94.134.164.124\n" +
//                                "84.189.213.221\n" +
//                                "93.133.117.37\n" +
//                                "2.244.92.28\n" +
//                                "91.45.161.169\n" +
//                                "78.48.6.42\n" +
//                                "77.10.167.152\n" +
//                                "188.104.120.213\n" +
//                                "88.150.109.97\n" +
//                                "213.196.209.248\n" +
//                                "84.138.160.148\n" +
//                                "87.177.247.167\n" +
//                                "89.12.72.120\n" +
//                                "84.138.160.172\n" +
//                                "2.204.134.22\n" +
//                                "91.49.238.129\n" +
//                                "77.10.215.252\n" +
//                                "89.245.33.16\n" +
//                                "77.189.127.165\n" +
//                                "91.65.20.47\n" +
//                                "77.23.204.6\n" +
//                                "93.215.163.40\n" +
//                                "79.217.177.186\n" +
//                                "80.146.121.13\n" +
//                                "84.178.253.212\n" +
//                                "94.114.133.31\n" +
//                                "93.255.20.123\n" +
//                                "87.165.83.104\n" +
//                                "79.216.166.165\n" +
//                                "79.249.58.249\n" +
//                                "92.252.119.36\n" +
//                                "87.145.67.200\n" +
//                                "84.159.212.238\n" +
//                                "93.242.143.55\n" +
//                                "31.150.35.202\n" +
//                                "77.10.149.215\n" +
//                                "80.138.172.85\n" +
//                                "77.11.98.77\n" +
//                                "95.91.238.111\n" +
//                                "87.145.229.154\n" +
//                                "79.221.74.223\n" +
//                                "88.153.203.68\n" +
//                                "92.117.104.126\n" +
//                                "77.182.27.221\n" +
//                                "46.114.59.19\n" +
//                                "93.211.110.205\n" +
//                                "95.90.177.22\n" +
//                                "77.182.132.49\n" +
//                                "84.155.176.104\n" +
//                                "77.11.235.208\n" +
//                                "46.223.3.169\n" +
//                                "37.201.199.210\n" +
//                                "91.49.192.250\n" +
//                                "91.248.156.173\n" +
//                                "91.11.55.148\n" +
//                                "89.0.107.198\n" +
//                                "80.129.212.144\n" +
//                                "37.201.147.128\n" +
//                                "85.22.87.96\n" +
//                                "79.205.166.139\n" +
//                                "91.41.82.113\n" +
//                                "79.201.36.26\n" +
//                                "84.158.246.247\n" +
//                                "93.135.175.126\n" +
//                                "178.12.193.51\n" +
//                                "149.224.142.138\n" +
//                                "188.193.250.111\n" +
//                                "77.0.30.228\n" +
//                                "91.8.185.4\n" +
//                                "93.254.160.91\n" +
//                                "77.1.4.105\n" +
//                                "89.14.120.224\n" +
//                                "84.154.220.108\n" +
//                                "95.91.248.11\n" +
//                                "46.5.2.245\n" +
//                                "87.151.62.12\n" +
//                                "93.221.187.215\n" +
//                                "93.219.134.191\n" +
//                                "91.96.219.23\n" +
//                                "84.182.208.109\n" +
//                                "217.248.71.225\n" +
//                                "95.90.91.132\n" +
//                                "93.238.128.140\n" +
//                                "92.201.240.187\n" +
//                                "146.0.126.160\n" +
//                                "87.133.87.17\n" +
//                                "89.16.144.177\n" +
//                                "78.49.142.250\n" +
//                                "95.223.76.99\n" +
//                                "77.181.161.90\n" +
//                                "95.33.179.66\n" +
//                                "91.52.238.105\n" +
//                                "37.85.108.70\n" +
//                                "93.135.3.163\n" +
//                                "88.69.158.254\n" +
//                                "91.64.52.87\n" +
//                                "77.190.89.97\n" +
//                                "62.226.8.250\n" +
//                                "93.233.169.14\n" +
//                                "77.3.137.141\n" +
//                                "93.222.251.248\n" +
//                                "80.145.29.188\n" +
//                                "84.166.58.193"
//                    ), BlackList.MatchType.OCTET3
//                ),
//                BlackList(listOf("123.123.123.123"), BlackList.MatchType.FULL)
//            )
//        )
////        println(p.blackList.flatMap { it.ipList })
//        p.parse().blockLast()
//    }
}