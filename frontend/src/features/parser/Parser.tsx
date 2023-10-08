import { useState } from "react";

import { Box, Button, Typography } from "@mui/material";
import s from "./Parser.module.css";
// import { selectCount } from './parserSlice';
import { Result } from "./result";
import { Settings } from "./settings";

export function Parser() {
  const [isSettingsOpen, setIsSettingsOpen] = useState(true);

  let [value, setValue] = useState(true);

  // const count = useAppSelector(selectCount);
  // const dispatch = useAppDispatch();
  // const [incrementAmount, setIncrementAmount] = useState('2');

  // const incrementValue = Number(incrementAmount) || 0;

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
              onClick={() => setIsSettingsOpen(false)}
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
