import {
  Alert,
  Box,
  BoxProps,
  Button,
  Checkbox,
  CircularProgress,
  FormControl,
  FormControlLabel,
  FormGroup,
  InputLabel,
  MenuItem,
  Radio,
  Select,
  Switch,
} from "@mui/material";
import Paper from "@mui/material/Paper";
import { FC, useState } from "react";
import { Input, RadioGroup } from "../components";

import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import { Modal } from "./Modal";
import {
  BlackListType,
  useGetBlackListsQuery,
  useUpdatePostMutation,
  useDeleteBlackListMutation,
} from "./api";
import { enabledBlackLists } from "../../../app/settingsData/settingsData";
import { useAppDispatch } from "../../../app/hooks";

export const BlackList: FC<BoxProps> = ({ sx }) => {
  const { data: blackLists, error, isLoading } = useGetBlackListsQuery("");
  const [open, setOpen] = useState(true);
  console.log("🚀 ~ file: BlackList.tsx:26 ~ open:", open);
  const [blackList, setBlackList] = useState<BlackListType | null>(null);

  const [matchType, setMatchType] = useState("");
  const [name, setName] = useState("");
  const [ipString, setIpString] = useState("");
  const [updatePost] = useUpdatePostMutation();
  const [deletePost] = useDeleteBlackListMutation();

  const dispatch = useAppDispatch();

  function switchBlackList(enabled: boolean, name: string) {
    if (enabled) dispatch(enabledBlackLists.actions.add(name));
    else dispatch(enabledBlackLists.actions.remove(name));
  }

  function saveBlacklist() {
    updatePost({ name, ipString, matchType });
  }

  if (isLoading) {
    return <CircularProgress />;
  }
  if (error) {
    return (
      <Alert severity="error">
        <>
          <h5>Ошибка</h5>
          <p>{JSON.stringify(error)}</p>
        </>
      </Alert>
    );
  }
  return (
    <Box sx={{ ...sx }}>
      <Box
        sx={{
          display: "grid",
          height: "40vh",
          columnGap: "1rem",
          gridTemplateColumns: "1.2fr 2fr",
          // rowGap: "2rem",
          gridTemplateRows: "2fr 4fr 2fr",
          gridTemplateAreas: ` 
          "name       list"
          "type       list"   
          "create     list"`,
        }}
      >
        <Input
          sx={{ gridArea: "name" }}
          label={"Сохранить черный список"}
          placeholder="Введите имя черного списка"
          value={name}
          onChange={(e) => setName((e.target as HTMLInputElement).value)}
        />
        <Button
          variant="contained"
          sx={{ mt: 5, gridArea: "create" }}
          onClick={saveBlacklist}
        >
          Создать
        </Button>

        <Input
          sx={{ gridArea: "list", height: "max-content", overflow: "auto" }}
          label={"Список IP"}
          multiline
          rows={13}
          value={ipString}
          onChange={(e) => setIpString((e.target as HTMLInputElement).value)}
        />
        <RadioGroup
          label={"Совпадение"}
          value={matchType}
          defaultValue={"OCTET2"}
          onChange={(e) => setMatchType((e.target as HTMLInputElement).value)}
          sx={{ gridArea: "type", mt: "4vh" }}
        >
          <FormControlLabel
            value="OCTET2"
            control={<Radio />}
            label="По второму октету"
            defaultChecked
          />
          <FormControlLabel
            value="OCTET3"
            control={<Radio />}
            label="По третьему октету"
          />
          <FormControlLabel
            value="FULL"
            control={<Radio />}
            label="Точное соответствие"
          />
        </RadioGroup>
      </Box>

      <Box sx={{ height: "49vh", width: "100%", mt: 2, overflow: "auto" }}>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell align="center">Использовать</TableCell>
                <TableCell align="center">Совпадение</TableCell>
                <TableCell align="center">Название списка</TableCell>
                <TableCell align="center">Кол-во IP в списке</TableCell>
                <TableCell align="center">X</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {blackLists?.map((row) => (
                <TableRow
                  key={row.name}
                  sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                >
                  <TableCell align="center">
                    <Switch
                      onChange={(e) =>
                        switchBlackList(e.target.checked, row.name)
                      }
                    />
                  </TableCell>
                  <TableCell align="center">
                    <FormControl fullWidth>
                      <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        // value={row.matchType}
                        defaultValue={row.matchType}
                      >
                        <MenuItem value={"OCTET2"}>По 2 октету</MenuItem>
                        <MenuItem value={"OCTET3"}>По 3 октету</MenuItem>
                        <MenuItem value={"FULL"}>Полное соответствие</MenuItem>
                      </Select>
                    </FormControl>
                  </TableCell>
                  <TableCell align="center">{row.name}</TableCell>
                  <TableCell align="center">
                    <Button
                      onClick={() => {
                        setBlackList(row);
                      }}
                    >
                      {row.ipString.split("\n").length}
                    </Button>
                  </TableCell>
                  <TableCell align="center">
                    <Button
                      variant="contained"
                      onClick={() => deletePost(row.name)}
                    >
                      Удалить
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Box>
      <Modal
        open={!!blackList}
        blackList={blackList}
        setBlackList={setBlackList}
      />
    </Box>
  );
};
