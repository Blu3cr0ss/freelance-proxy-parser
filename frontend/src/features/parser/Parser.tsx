import { useState } from "react";

import { Box, Button, Typography } from "@mui/material";
import s from "./Parser.module.css";
// import { selectCount } from './parserSlice';
import { Result } from "./result";
import { Settings } from "./settings";
import {
  apiKeysStringSelector,
  enabledBlackListsSelector,
  ipStringSelector,
} from "../../app/settingsData/settingsData";
import { useAppSelector } from "../../app/hooks";
import { fetchData, resultDataSelector } from "../../app/resultData/resultData";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../../app/store";

export function Parser() {
  const [isSettingsOpen, setIsSettingsOpen] = useState(true);
  const dispatch = useDispatch<AppDispatch>();

  let [value, setValue] = useState(true);

  const ipString = useAppSelector(ipStringSelector);
  const apiKeys = useAppSelector(apiKeysStringSelector);
  const blackLists = useAppSelector(enabledBlackListsSelector);

  return (
    <div className="wrapper">
      <Box>
        <Box className={s.header}>
          <Typography variant="h4" sx={{ fontWeight: "bold" }}>
            Парсер&nbsp;Fraud&nbsp;score
          </Typography>
          <Typography>Всего&nbsp;ссылок:&nbsp;12</Typography>
          <Box>
            <Button
              variant="contained"
              onClick={() => {
                setIsSettingsOpen(false);
                dispatch(
                  fetchData({
                    proxy: ipString,
                    apiKeys,
                    blackList: blackLists,
                    maxFraudScore: 100,
                  })
                );
              }}
            >
              Запустить
            </Button>
          </Box>
          <Box sx={{ display: "flex", justifyContent: "end" }}>
            <Button variant="contained">Лог&nbsp;событий</Button>
            <Button
              variant="contained"
              sx={{ mx: 2 }}
              onClick={() => setIsSettingsOpen((v) => !v)}
            >
              {isSettingsOpen ? "Результаты" : "Настройки"}
            </Button>
          </Box>
        </Box>
      </Box>

      <Settings sx={{ mt: 2, display: isSettingsOpen ? "grid" : "none" }} />
      <Result sx={{ mt: 2, display: !isSettingsOpen ? "grid" : "none" }} />
    </div>
  );
}
